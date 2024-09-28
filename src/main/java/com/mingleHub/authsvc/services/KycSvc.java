package com.mingleHub.authsvc.services;

import com.mingleHub.authsvc.dto.common.MessageResponse;
import com.mingleHub.authsvc.dto.sms.SendOtpRequest;
import com.mingleHub.authsvc.dto.sms.VerifyOtpRequest;

public interface KycSvc {
	MessageResponse sendOtp(SendOtpRequest sendOtpRequest, String userId);
	
	MessageResponse verifyOtp(VerifyOtpRequest verifyOtpRequest, String userId);
}
