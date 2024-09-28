package com.mingleHub.authsvc.dto.sms;

import com.mingleHub.authsvc.constants.Gateways;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class SendOtpRequest {
	private final String gateway = Gateways.E_LABS;
	@NotNull @NotEmpty
    private String number;
    private String userId;
}
