package com.mingleHub.authsvc.services.impl;

import com.mingleHub.authsvc.auth.impl.JwtSvcImpl;
import com.mingleHub.authsvc.dao.User;
import com.mingleHub.authsvc.dto.RegisterRequest;
import com.mingleHub.authsvc.dto.auth.AuthenticationRequest;
import com.mingleHub.authsvc.dto.auth.AuthenticationResponse;
import com.mingleHub.authsvc.exceptions.DuplicateEmailException;
import com.mingleHub.authsvc.exceptions.UserNotFoundException;
import com.mingleHub.authsvc.repositories.UserInfoRepository;
import com.mingleHub.authsvc.services.AuthSvc;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
public class AuthSvcImpl implements AuthSvc {
    private final UserInfoRepository userInfoRepository;
    private final JwtSvcImpl jwtSvc;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AuthSvcImpl(
            UserInfoRepository userInfoRepository,
            JwtSvcImpl jwtSvc,
            AuthenticationManager authenticationManager,
            PasswordEncoder passwordEncoder
    ) {
        this.userInfoRepository = userInfoRepository;
        this.jwtSvc = jwtSvc;
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public AuthenticationResponse register(RegisterRequest registerRequest){

        Optional<User> userDao = userInfoRepository.findByEmail(registerRequest.getEmail());
        if (userDao.isPresent()) {
            log.error("ERROR :: {} id already sync with another account", registerRequest.getEmail());
            throw new DuplicateEmailException("email already sync with another account");
        }

        User userObject = new User().setId(UUID.randomUUID())
                .setEmail(registerRequest.getEmail())
                .setPassword(passwordEncoder.encode(registerRequest.getPassword()))
                .setRole(registerRequest.getRole())
                .setFirstName(registerRequest.getFirstName())
                .setLastName(registerRequest.getLastName());

        userInfoRepository.save(userObject);
        String jwtToken = jwtSvc.generateToken(userObject);
        return new AuthenticationResponse().setAccessToken(jwtToken);
    }

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        var user = userInfoRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new UserNotFoundException("User not found for email: " + request.getEmail()));

        String jwtToken = jwtSvc.generateToken(user);
        return new AuthenticationResponse().setAccessToken(jwtToken);
    }
}
