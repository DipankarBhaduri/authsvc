package com.mingleHub.authsvc.dto.auth;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class AuthenticationResponse {
    private String accessToken;
}
