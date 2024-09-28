package com.mingleHub.authsvc.controllers;

import com.mingleHub.authsvc.auth.JwtSvc;
import com.mingleHub.authsvc.dto.common.MessageResponse;
import com.mingleHub.authsvc.dto.sms.SendOtpRequest;
import com.mingleHub.authsvc.dto.sms.VerifyOtpRequest;
import com.mingleHub.authsvc.services.KycSvc;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/v1/kyc")
public class KycController {
    private final KycSvc kycSvc;
	private final JwtSvc jwtSvc;

    @Autowired
    public KycController(
			KycSvc kycSvc,
			JwtSvc jwtSvc
	) {
        this.kycSvc = kycSvc;
		this.jwtSvc = jwtSvc;
    }

    @PostMapping("/otp/send")
    public MessageResponse sendOtp (
			HttpServletRequest request,
            @RequestBody @Valid SendOtpRequest sendOtpRequest
    ) {
        log.info("INFO :: send otp request for user :: {}, and mobile :: {}",
				jwtSvc.getUser(request), sendOtpRequest.getNumber());
        return kycSvc.sendOtp(sendOtpRequest, jwtSvc.getUser(request));
    }

    @PostMapping("/verify/otp")
    public MessageResponse verifyOtp (
			HttpServletRequest request,
            @RequestBody VerifyOtpRequest verifyOtpRequest
    ) {
        log.info("INFO :: send otp verify request for user :: {}", jwtSvc.getUser(request));
        return kycSvc.verifyOtp(verifyOtpRequest, jwtSvc.getUser(request));
    }
}