package com.mingleHub.authsvc.gateways.impl;

import com.mingleHub.authsvc.constants.EventType;
import com.mingleHub.authsvc.constants.Gateways;
import com.mingleHub.authsvc.dao.EventLogDAO;
import com.mingleHub.authsvc.dao.User;
import com.mingleHub.authsvc.dto.common.MessageResponse;
import com.mingleHub.authsvc.dto.sms.OtpResponse;
import com.mingleHub.authsvc.dto.sms.SendOtpRequest;
import com.mingleHub.authsvc.dto.sms.VerifyOtpRequest;
import com.mingleHub.authsvc.exceptions.*;
import com.mingleHub.authsvc.gateways.SMSGateways;
import com.mingleHub.authsvc.repositories.EventLogRepo;
import com.mingleHub.authsvc.repositories.UserInfoRepository;
import com.mingleHub.authsvc.utils.EventLogHelper;
import com.mingleHub.authsvc.utils.RestTemplateHelper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Component
public class SMSGatewaysImpl implements SMSGateways {
    private final String token;
    private final String url;
	private final String OTP_MATCHED = "OTP Matched";
    private final RestTemplateHelper restTemplateHelper;
	private final EventLogRepo eventLogRepo;
	private final UserInfoRepository userInfoRepository;
	private final EventLogHelper eventLogHelper;

    @Autowired
    public SMSGatewaysImpl(
            @Value("${eLabs.sms.token}") String token,
            @Value("${eLabs.sms.url}") String url,
            RestTemplateHelper restTemplateHelper,
			EventLogHelper eventLogHelper,
			EventLogRepo eventLogRepo,
			UserInfoRepository userInfoRepository
    ) {
        this.token = token;
        this.url = url;
        this.restTemplateHelper = restTemplateHelper;
		this.eventLogHelper = eventLogHelper;
		this.eventLogRepo = eventLogRepo;
		this.userInfoRepository = userInfoRepository;
    }

    @Override
    public String getName() {
        return Gateways.E_LABS;
    }

    @Override
    public MessageResponse sendOtp(SendOtpRequest sendOtpRequest, String userId) {
		validateRequest(sendOtpRequest);
		sendOtpRequest.setUserId(userId);
		
        StringBuilder stringBuilder = new StringBuilder()
                .append("/API/V1/")
                .append(token)
                .append("/SMS/")
                .append(updateMobileNumber(sendOtpRequest.getNumber()))
                .append("/AUTOGEN/AUTH_OTP");
        try {
			OtpResponse otpResponse = restTemplateHelper.getFromExternalServer(url, stringBuilder.toString(), OtpResponse.class);
			createEventLog(sendOtpRequest, otpResponse, userId);
			return new MessageResponse().setMessage("OTP send successfully");
        } catch (Exception e) {
            log.error("ERROR :: for the user :: {}, error details :: {}", sendOtpRequest.getUserId(), e.getMessage());
            throw new InvalidRequestException(
					String.format("ERROR :: for in sending otp to :: %s, for user :: %s",
							sendOtpRequest.getNumber(), sendOtpRequest.getUserId())
			);
        }
    }
	
	private void validateRequest(SendOtpRequest sendOtpRequest) {
		if (sendOtpRequest.getNumber().length() != 10) {
			throw new InvalidNumberException("Phone number must be 10 digit");
		}

		if (!sendOtpRequest.getNumber().matches("\\d+")) {
			throw new InvalidNumberException("Phone number must contain only digits");
		}
		
		userInfoRepository.findByPhoneNumber(sendOtpRequest.getNumber())
				.ifPresent(user -> {
					throw new FraudulentRiskException("Phone number already linked with another account");
				});
	}
	
	private void createEventLog(
			SendOtpRequest sendOtpRequest,
			OtpResponse otpResponse,
			String userId
	) {
		Map<String, String> data = Map.of(
				"number", sendOtpRequest.getNumber()
		);
		eventLogHelper.createEventLog(EventType.OTP_INITIATE, userId, data, otpResponse.getDetails());
	}
	
	public String updateMobileNumber(String mobileNumber){
        if(!mobileNumber.startsWith("+") && mobileNumber.length() == 10)
            mobileNumber = "+91" + mobileNumber;
        return mobileNumber;
    }

    @Override
    public MessageResponse verifyOtp(VerifyOtpRequest verifyOtpRequest, String userId) {
		String sessionId = getSessionIdForVerifyOTP(userId);
        StringBuilder stringBuilder = new StringBuilder()
                .append("/API/V1/")
                .append(token)
                .append("/SMS/VERIFY/")
                .append(sessionId)
                .append("/")
                .append(verifyOtpRequest.getOtp());
		OtpResponse otpResponse = null;
        try {
			otpResponse = restTemplateHelper.getFromExternalServer(
                    url,
                    stringBuilder.toString(),
					OtpResponse.class
            );
        } catch (HttpClientErrorException e) {
			log.error("ERROR :: for the user during verify otp :: {}, error details :: {}", verifyOtpRequest.getUserId(), e.getMessage());
			throw new InvalidOTPException("Invalid OTP");
		} catch (Exception e) {
            log.error("ERROR :: for the user during verify otp :: {}, error details :: {}", verifyOtpRequest.getUserId(), e.getMessage());
            throw new InvalidRequestException("ERROR :: during verifying otp :: ");
        }
		return updateMobileNumberInDatabase(otpResponse, userId, sessionId);
    }
	
	private MessageResponse updateMobileNumberInDatabase(
			OtpResponse otpResponse, String userId, String sessionId
	) {
		if (!OTP_MATCHED.equals(otpResponse.getDetails())) {
			throw new InvalidOTPException("Invalid OTP");
		}
		
		Optional<User> user = userInfoRepository.findById(UUID.fromString(userId));
		if (StringUtils.isNotEmpty(user.get().getPhoneNumber())) {
			throw new PhoneAlreadyVerifiedException("Phone already verified");
		}
		
		user.get().setPhoneNumber(getPhoneNumberFromSessionId(userId, sessionId)).setPhoneVerified(true);
		userInfoRepository.save(user.get());
		return new MessageResponse().setMessage("Phone verified Successfully");
	}
	
	private String getSessionIdForVerifyOTP(String userId) {
		return eventLogRepo.findFirstByUserIdAndEventTypeOrderByIdDesc(userId, EventType.OTP_INITIATE)
					   .map(EventLogDAO::getReferenceId)
					   .orElseThrow(
							   () -> new InvalidRequestException("Before verification, please generate the OTP")
					   );
	}
	
	private String getPhoneNumberFromSessionId(String userId, String SessionId) {
		EventLogDAO eventLogDAO = eventLogRepo.findFirstByUserIdAndEventTypeOrderByIdDesc(userId, EventType.OTP_INITIATE)
					   .orElseThrow(
							   () -> new InvalidRequestException("Before verification, please generate the OTP")
					   );
		
		return eventLogDAO.getData().split("\"")[3];
	}
}