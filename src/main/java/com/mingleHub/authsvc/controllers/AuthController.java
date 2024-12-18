package com.mingleHub.authsvc.controllers;

import com.mingleHub.authsvc.dto.onboarding.RegisterRequest;
import com.mingleHub.authsvc.dto.auth.AuthenticationRequest;
import com.mingleHub.authsvc.dto.auth.AuthenticationResponse;
import com.mingleHub.authsvc.services.AuthSvc;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import static com.mingleHub.authsvc.messages.InfoLogs.AUTHENTICATE_FOR_USER;
import static com.mingleHub.authsvc.messages.InfoLogs.REGISTER_FOR_USER;

@Slf4j
@RestController
@RequestMapping("/v1/auth")
public class AuthController {

    private final AuthSvc authSvc;

    @Autowired
    public AuthController(
            AuthSvc authSvc
    ) {
        this.authSvc = authSvc;
    }

    @PostMapping("/register")
    public AuthenticationResponse register(
            @RequestBody @Valid RegisterRequest registerRequest
    ) {
        log.info(REGISTER_FOR_USER, registerRequest.getEmail());
        return authSvc.register(registerRequest);
    }

    @PostMapping("/authenticate")
    public AuthenticationResponse authenticate(
            @RequestBody AuthenticationRequest request
    ) {
        log.info(AUTHENTICATE_FOR_USER, request.getEmail());
        return authSvc.authenticate(request);
    }
}