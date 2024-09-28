package com.mingleHub.authsvc.gateways.impl;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

@Lazy
@Getter
@Configuration
public class CashfreePanConfig {

    @Value("${pan.cashfree.host}")
    private String host;

    @Value("${pan.cashfree.panSync}")
    private String panVerificationPath;

    @Value("${pan.cashfree.panSync}")
    private String panStatusPath;

    @Value("${kyc.cashfree.clientID}")
    private String clientID;

    @Value("${kyc.cashfree.clientSecret}")
    private String clientSecret;

}
