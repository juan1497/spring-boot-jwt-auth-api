package com.juan1497.securityjwt.service;

import com.juan1497.securityjwt.dto.AuthResponse;
import com.juan1497.securityjwt.dto.LoginRequest;
import com.juan1497.securityjwt.dto.SignupRequest;

public interface IAuthService {

    String signup(SignupRequest request);

    AuthResponse login(LoginRequest request);
}
