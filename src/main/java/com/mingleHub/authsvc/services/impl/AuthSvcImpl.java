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
					log.error("ERROR :: {} is already synced with another account", registerRequest.getEmail());
					throw new DuplicateEmailException(String.format(String.format("%s is already synced with another account", registerRequest.getEmail())));
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
					"ERROR :: either username :: %s or password %s is incorrect", request.getEmail(), request.getPassword())
			);
			throw new IncorrectCredentialsException("either username or password is incorrect");
		}

        var user = userInfoRepository.findByEmail(request.getEmail())
						   .orElseThrow(() -> new UserNotFoundException(
										   String.format("user not found with email: %s" ,request.getEmail())
								   )
						   );

        String jwtToken = jwtSvc.generateToken(user);
        return new AuthenticationResponse().setAccessToken(jwtToken);
    }
}
