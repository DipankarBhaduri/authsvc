package com.mingleHub.authsvc.controllers;

import com.mingleHub.authsvc.auth.impl.JwtSvcImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/v1/admin")
public class AdminController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private JwtSvcImpl jwtSvc;

    @GetMapping
    public String getAdmin(HttpServletRequest request) {
        System.out.println(request);
        final String authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String jwt = authorizationHeader.substring(7);
            String user = jwtSvc.extractUsername(jwt);
            System.out.println(user);
        }
        sendSmsAutoGenOtpWithTemplate("6290319466");
        return "Secured Endpoint :: GET - Admin controller";
    }

    public Object sendSmsAutoGenOtpWithTemplate(String mobileNumber){
        String endPoint = "/API/V1/ef1674ab-4e49-11eb-8153-0200cd936042"+"/SMS/"+ updateMobileNumber(mobileNumber) +"/AUTOGEN" + "/" + "AUTH_OTP";
        return getFromExternalServer("https://2factor.in", endPoint);
    }

    public String updateMobileNumber(String mobileNumber){
        if(!mobileNumber.startsWith("+") && mobileNumber.length() == 10)
            mobileNumber = "+91" + mobileNumber;
        return mobileNumber;
    }

    private HttpHeaders getHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }

    public Object getFromExternalServer(String hostname, String endpoint) {
        try {
            HttpEntity<?> request = new HttpEntity<>(null, getHeaders());
            ResponseEntity <Object> respEntity = restTemplate.exchange(hostname + endpoint, HttpMethod.GET, request, Object.class);
            System.out.println(respEntity.getBody());
            return respEntity.getBody();
        } catch (Exception e) {
            throw new RuntimeException("error", e);
        }
    }

    @PostMapping("/{otp}/{session}")
    public String post(@PathVariable String otp, @PathVariable String session ) {
        verifySmsOtp(session ,otp);
        return "Secured Endpoint :: POST - Admin controller";
    }

    public Object verifySmsOtp(String session_id, String otp_entered_by_user){
        String endPoint = "/API/V1/ef1674ab-4e49-11eb-8153-0200cd936042"+"/SMS/VERIFY/"+session_id+"/" + otp_entered_by_user;
        return getFromExternalServer("https://2factor.in", endPoint);
    }
}