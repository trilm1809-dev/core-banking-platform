package com.corebankingplatform.server.services.interfaces;

import com.corebankingplatform.server.dto.request.auth.SignInRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface IAuthService {
    public ResponseEntity<?> signIn(SignInRequest signInRequest);
}
