package com.mingleHub.authsvc.dto.sms;

import com.mingleHub.authsvc.constants.Gateways;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class VerifyOtpRequest {
	private final String gateway = Gateways.E_LABS;
    private String userId;
    private String otp;
}
