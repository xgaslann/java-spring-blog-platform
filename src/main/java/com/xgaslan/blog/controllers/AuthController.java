package com.xgaslan.blog.controllers;

import com.xgaslan.blog.domain.models.user.AuthModel;
import com.xgaslan.blog.domain.models.user.LoginModel;
import com.xgaslan.blog.services.IAuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final IAuthenticationService service;

    @PostMapping("/login")
    public ResponseEntity<AuthModel> login(@RequestBody LoginModel model){
        UserDetails userDetails = service.authenticate(model.getEmail(), model.getPassword());

        String token = service.generateToken(userDetails);

        AuthModel authModel = AuthModel.builder()
                .token(token) // Replace with actual token generation logic
                .expiresIn(86400) // Set the expiration time as needed
                .build();

        return ResponseEntity.ok(authModel);
    }
}
