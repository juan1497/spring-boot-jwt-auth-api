package com.juan1497.securityjwt.service;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.juan1497.securityjwt.dto.AuthResponse;
import com.juan1497.securityjwt.dto.AuthUserResponse;
import com.juan1497.securityjwt.dto.LoginRequest;
import com.juan1497.securityjwt.dto.SignupRequest;
import com.juan1497.securityjwt.excepciones.CredentialsIncorrectException;
import com.juan1497.securityjwt.excepciones.EmailAlreadyExistsException;
import com.juan1497.securityjwt.model.Role;
import com.juan1497.securityjwt.model.User;
import com.juan1497.securityjwt.model.UserRoleAssignment;
import com.juan1497.securityjwt.repository.RoleRepository;
import com.juan1497.securityjwt.repository.UserRepository;
import com.juan1497.securityjwt.repository.UserRoleAssignmentRepository;
import com.juan1497.securityjwt.security.JwtService;

import jakarta.transaction.Transactional;

@Service
public class AuthService implements IAuthService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserRoleAssignmentRepository userRoleAssignmentRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthService(
            UserRepository userRepository,
            RoleRepository roleRepository,
            UserRoleAssignmentRepository userRoleAssignmentRepository,
            PasswordEncoder passwordEncoder,
            JwtService jwtService) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.userRoleAssignmentRepository = userRoleAssignmentRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    @Transactional
    @Override
    public String signup(SignupRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new EmailAlreadyExistsException("Email already registered");
        }
        Role defaultRole = roleRepository.findByName("ROLE_USER")
                .orElseThrow(() -> new RuntimeException("Default role not found"));
        User user = new User();
        user.setId(UUID.randomUUID());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setEnabled(true);
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());

        User savedUser = userRepository.save(user);

        UserRoleAssignment assignment = new UserRoleAssignment();
        assignment.setId(UUID.randomUUID());
        assignment.setUser(savedUser);
        assignment.setRole(defaultRole);
        assignment.setAssignedAt(LocalDateTime.now());
        assignment.setActive(true);

        userRoleAssignmentRepository.save(assignment);

        return "User registered successfully";
    }

    @Override
    public AuthResponse login(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new CredentialsIncorrectException("Invalid email or password"));
        if (!user.isEnabled()) {
            throw new CredentialsIncorrectException("Invalid email or password");
        }
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new CredentialsIncorrectException("Invalid email or password");
        }
        String accessToken = jwtService.generateToken(user);
        AuthUserResponse userResponse = new AuthUserResponse(
                user.getId(),
                user.getEmail());
        return new AuthResponse(
                accessToken,
                "Bearer",
                jwtService.getJwtExpiration(),
                userResponse);
    }

}
