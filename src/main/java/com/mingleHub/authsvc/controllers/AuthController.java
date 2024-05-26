package com.mingleHub.authsvc.controllers;

import com.mingleHub.authsvc.dto.RegisterRequest;
import com.mingleHub.authsvc.dto.auth.AuthenticationRequest;
import com.mingleHub.authsvc.dto.auth.AuthenticationResponse;
import com.mingleHub.authsvc.services.AuthSvc;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
            @RequestBody RegisterRequest registerRequest
    ) {
        log.info("INFO :: register for user :: {}", registerRequest.getEmail());
        return authSvc.register(registerRequest);
    }


    @PostMapping("/authenticate")
    public AuthenticationResponse authenticate(
            @RequestBody AuthenticationRequest request
    ) {
        log.info("INFO :: authenticate for user :: {}", request.getEmail());
        return authSvc.authenticate(request);
    }
}

