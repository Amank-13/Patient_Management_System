package org.aman.patientmangement.authservice.service;

import org.aman.patientmangement.authservice.dto.LoginRequestDTO;
import org.aman.patientmangement.authservice.util.JwtUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;
    private final UserService userService;

    public AuthService(JwtUtil jwtUtil, PasswordEncoder passwordEncoder, UserService userService) {
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
    }

    public Optional<String>  authenticate(LoginRequestDTO loginRequestDTO) {

        return userService
                .findByEmail(loginRequestDTO.getEmail())
                .filter(u -> passwordEncoder.matches(loginRequestDTO.getPassword(),
                        u.getPassword()))
                .map(u -> jwtUtil.generateToken(u.getEmail(),u.getRole()));

    }
}
