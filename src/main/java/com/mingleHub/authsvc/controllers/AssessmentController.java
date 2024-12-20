package com.mingleHub.authsvc.controllers;

import com.mingleHub.authsvc.auth.JwtSvc;
import com.mingleHub.authsvc.dto.auth.AuthenticationResponse;
import com.mingleHub.authsvc.dto.onboarding.RegisterRequest;
import com.mingleHub.authsvc.services.AuthSvc;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.mingleHub.authsvc.messages.InfoLogs.REGISTER_FOR_USER;

@Slf4j
@RestController
@RequestMapping("/v1/assessment")
public class AssessmentController {

    private final JwtSvc jwtSvc;

    @Autowired
    public AssessmentController(
            JwtSvc jwtSvc
    ) {
        this.jwtSvc = jwtSvc;
    }

    @PostMapping("/question")
    public String register(
            HttpServletRequest request
    ) {
        return jwtSvc.getUser(request);
    }
}
