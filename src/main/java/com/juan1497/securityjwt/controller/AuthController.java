package com.juan1497.securityjwt.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.juan1497.securityjwt.dto.AuthResponse;
import com.juan1497.securityjwt.dto.LoginRequest;
import com.juan1497.securityjwt.dto.SignupRequest;
import com.juan1497.securityjwt.service.IAuthService;

import jakarta.validation.Valid;

@RestController
@Validated
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {
    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);
    private final IAuthService authService;

    public AuthController(IAuthService authService) {
        this.authService = authService;

    }

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@Valid @RequestBody SignupRequest request) {
        logger.info("Creando usuario con email: {}", request.getEmail());
        String result = authService.signup(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginRequest request) {
        logger.info("Intento de inicio de sesión para email: {}", request.getEmail());
        AuthResponse result = authService.login(request);
        return ResponseEntity.ok(result);
    }

}
