package com.juan1497.securityjwt.dto;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AuthUserResponse {
    private UUID id;
    private String email;
}
