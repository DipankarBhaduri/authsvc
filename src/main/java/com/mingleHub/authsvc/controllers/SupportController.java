package com.mingleHub.authsvc.controllers;

import com.mingleHub.authsvc.dto.auth.AuthenticationResponse;
import com.mingleHub.authsvc.dto.onboarding.RegisterRequest;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import static com.mingleHub.authsvc.messages.InfoLogs.REGISTER_FOR_USER;

@Slf4j
@RestController
@RequestMapping("/v1/support")
public class SupportController {

//    @PostMapping("/report")
//    public AuthenticationResponse supportIssue(
//            @RequestBody @Valid RegisterRequest registerRequest
//    ) {
//        log.info(REGISTER_FOR_USER, registerRequest.getEmail());
//        return authSvc.register(registerRequest);
//    }
}
