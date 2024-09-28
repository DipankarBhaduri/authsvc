package com.mingleHub.authsvc.services.impl;

import com.mingleHub.authsvc.dto.common.MessageResponse;
import com.mingleHub.authsvc.dto.sms.SendOtpRequest;
import com.mingleHub.authsvc.dto.sms.VerifyOtpRequest;
import com.mingleHub.authsvc.gateways.SMSGateways;
import com.mingleHub.authsvc.services.KycSvc;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Component
public class KycSvcImpl implements KycSvc {
	private final Map<String, SMSGateways> smsGatewaysMap;
	
	@Autowired
	public KycSvcImpl(Set<SMSGateways> smsGatewaysSet){
		this.smsGatewaysMap =
				smsGatewaysSet.stream()
						.collect(Collectors.toMap((SMSGateways::getName), val -> val));
	}
	
	@Override
	public MessageResponse sendOtp(SendOtpRequest sendOtpRequest, String userId) {
		return smsGatewaysMap
					   .get(sendOtpRequest.getGateway())
					   .sendOtp(sendOtpRequest, userId);
	}
	
	@Override
	public MessageResponse verifyOtp(VerifyOtpRequest verifyOtpRequest, String userId) {
		return smsGatewaysMap
					   .get(verifyOtpRequest.getGateway())
					   .verifyOtp(verifyOtpRequest, userId);
	}
}