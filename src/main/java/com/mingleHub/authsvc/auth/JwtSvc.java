package com.mingleHub.authsvc.auth;

import org.springframework.security.core.userdetails.UserDetails;

public interface JwtSvc {
    String generateToken(UserDetails user);
}
