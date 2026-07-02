package com.corebankingplatform.server.services.implementation;

import com.corebankingplatform.server.dto.request.auth.SignInRequest;
import com.corebankingplatform.server.dto.response.GenericResponse;
import com.corebankingplatform.server.dto.response.auth.SignInResponse;
import com.corebankingplatform.server.entites.User;
import com.corebankingplatform.server.repositories.interfaces.UserRepository;
import com.corebankingplatform.server.security.JwtService;
import com.corebankingplatform.server.services.interfaces.IAuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AuthService implements IAuthService {
    private JwtService jwtService;
    private UserRepository userRepository;

    public AuthService(JwtService jwtService, UserRepository userRepository) {
        this.jwtService = jwtService;
        this.userRepository = userRepository;
    }

    @Override
    public ResponseEntity<GenericResponse<SignInResponse>> signIn(SignInRequest signInRequest) {
        User user = userRepository.findByEmailAndPassword(signInRequest.getEmail(), signInRequest.getPassword());
        if (user != null) {
            String token = jwtService.generateToken(user);
            SignInResponse signInResponse = new SignInResponse();
            signInResponse.setAccessToken(token);
            return ResponseEntity.ok(GenericResponse.success(signInResponse));

        } else if (user == null) {
            return ResponseEntity.ok(GenericResponse.failure("Invalid email or password"));
        }
        return ResponseEntity.ofNullable(GenericResponse.failure("Invalid email or password"));

    }
}
