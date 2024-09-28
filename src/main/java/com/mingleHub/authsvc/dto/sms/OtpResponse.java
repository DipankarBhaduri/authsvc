package com.mingleHub.authsvc.dto.sms;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class OtpResponse {
    @JsonProperty("Status")
    private String status;

    @JsonProperty("Details")
    private String details;
}