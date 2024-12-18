package com.mingleHub.authsvc.services.impl;

import com.mingleHub.authsvc.auth.impl.JwtSvcImpl;
import com.mingleHub.authsvc.constants.Role;
import com.mingleHub.authsvc.dao.User;
import com.mingleHub.authsvc.dto.onboarding.RegisterRequest;
import com.mingleHub.authsvc.dto.auth.AuthenticationRequest;
import com.mingleHub.authsvc.dto.auth.AuthenticationResponse;
import com.mingleHub.authsvc.exceptions.DuplicateEmailException;
import com.mingleHub.authsvc.exceptions.IncorrectCredentialsException;
import com.mingleHub.authsvc.exceptions.UserNotFoundException;
import com.mingleHub.authsvc.repositories.UserInfoRepository;
import com.mingleHub.authsvc.services.AuthSvc;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.UUID;

import static com.mingleHub.authsvc.messages.ErrorLogs.*;
import static com.mingleHub.authsvc.messages.ErrorMessages.*;

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
		
		userInfoRepository.findByEmail(registerRequest.getEmail())
				.ifPresent(user -> {
					log.error(EMAIL_SYNCED_WITH_ANOTHER_ACCOUNT, registerRequest.getEmail());
                    throw new DuplicateEmailException(DUPLICATE_EMAIL);
				});
		
		User userObject = new User().setId(UUID.randomUUID())
                .setEmail(registerRequest.getEmail())
                .setPassword(passwordEncoder.encode(registerRequest.getPassword()))
                .setRole(Role.USER)
                .setFirstName(registerRequest.getFirstName())
                .setLastName(registerRequest.getLastName());

        userInfoRepository.save(userObject);
        String jwtToken = jwtSvc.generateToken(userObject);
        return new AuthenticationResponse().setAccessToken(jwtToken);
    }

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(
							request.getEmail(),
							request.getPassword()
					)
			);
		} catch (BadCredentialsException e) {
			log.error(String.format(
                    BAD_CREDENTIALS_ERROR_LOG, request.getEmail(), request.getPassword())
			);
			throw new IncorrectCredentialsException(INCORRECT_CREDENTIALS);
		}

        var user = userInfoRepository.findByEmail(request.getEmail())
						   .orElseThrow(() -> {
                               log.error(USER_NOT_FOUND_WITH_EMAIL_ID, request.getEmail());
                               return new UserNotFoundException(USER_NOT_FOUND);
                           });

        String jwtToken = jwtSvc.generateToken(user);
        return new AuthenticationResponse().setAccessToken(jwtToken);
    }
}