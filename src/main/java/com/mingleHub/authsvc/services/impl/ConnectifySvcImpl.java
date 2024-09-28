package com.mingleHub.authsvc.services.impl;

import com.mingleHub.authsvc.dto.sms.OtpResponse;
import com.mingleHub.authsvc.dto.sms.SendOtpRequest;
import com.mingleHub.authsvc.dto.sms.VerifyOtpRequest;
import com.mingleHub.authsvc.services.ConnectifySvc;
import com.mingleHub.authsvc.utils.RestTemplateHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ConnectifySvcImpl implements ConnectifySvc {

    @Value("${app.config.connectifysvc}")
    private String uri;

    private final String SEND_OTP = "/v1/kyc/send/otp";
    private final String VERIFY_OTP = "/v1/kyc/verify/otp";
    private final RestTemplateHelper restTemplateHelper;


    public ConnectifySvcImpl (
            RestTemplateHelper restTemplateHelper
    ) {
        this.restTemplateHelper = restTemplateHelper;
    }

    @Override
    public OtpResponse sendOtp(SendOtpRequest sendOtpRequest) {
        return restTemplateHelper.postForObject(uri, SEND_OTP, sendOtpRequest, OtpResponse.class);
    }

    @Override
    public OtpResponse verifyOtp(VerifyOtpRequest verifyOtpRequest) {
        return restTemplateHelper.postForObject(uri, VERIFY_OTP, verifyOtpRequest, OtpResponse.class);
    }
}
