package com.mingleHub.authsvc.gateways;

import com.mingleHub.authsvc.dto.common.MessageResponse;
import com.mingleHub.authsvc.dto.sms.OtpResponse;
import com.mingleHub.authsvc.dto.sms.SendOtpRequest;
import com.mingleHub.authsvc.dto.sms.VerifyOtpRequest;

public interface SMSGateways {
    String getName();
	
	MessageResponse sendOtp(SendOtpRequest sendOtpRequest, String userId);
	
	MessageResponse verifyOtp(VerifyOtpRequest verifyOtpRequest, String userId);
}
