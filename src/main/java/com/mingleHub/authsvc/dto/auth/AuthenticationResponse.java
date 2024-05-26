package com.mingleHub.authsvc.dto.auth;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class AuthenticationResponse {
    @JsonProperty("access_token")
    private String accessToken;
}
