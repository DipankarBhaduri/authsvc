package com.mingleHub.authsvc.services;

import com.mingleHub.authsvc.dto.sms.OtpResponse;
import com.mingleHub.authsvc.dto.sms.SendOtpRequest;
import com.mingleHub.authsvc.dto.sms.VerifyOtpRequest;

public interface ConnectifySvc {
    OtpResponse sendOtp(SendOtpRequest sendOtpRequest);

    OtpResponse verifyOtp(VerifyOtpRequest verifyOtpRequest);
}
