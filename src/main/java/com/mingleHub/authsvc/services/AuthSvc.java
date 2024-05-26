package com.mingleHub.authsvc.services;

import com.mingleHub.authsvc.dto.RegisterRequest;
import com.mingleHub.authsvc.dto.auth.AuthenticationRequest;
import com.mingleHub.authsvc.dto.auth.AuthenticationResponse;

public interface AuthSvc {
    AuthenticationResponse register(RegisterRequest registerRequest);

    AuthenticationResponse authenticate(AuthenticationRequest request);
}
