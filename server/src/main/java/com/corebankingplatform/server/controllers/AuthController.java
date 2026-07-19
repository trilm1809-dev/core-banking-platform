package com.corebankingplatform.server.controllers;

import com.corebankingplatform.server.dto.request.auth.SignInRequest;
import com.corebankingplatform.server.dto.response.GenericResponse;
import com.corebankingplatform.server.dto.response.auth.SignInResponse;
import com.corebankingplatform.server.services.implementation.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/signin")
    public ResponseEntity<GenericResponse<SignInResponse>> signIn(@RequestBody SignInRequest signInRequest) {
        return authService.signIn(signInRequest);
    }

//    @PostMapping("/signup")
//    public ResponseEntity<?> signUp(  ){
//
//    }
}
