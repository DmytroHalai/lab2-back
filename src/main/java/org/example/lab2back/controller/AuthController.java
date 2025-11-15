package org.example.lab2back.controller;

import jakarta.validation.Valid;
import org.example.lab2back.dto.AuthRequest;
import org.example.lab2back.dto.AuthResponse;
import org.example.lab2back.dto.UserCreateDto;
import org.example.lab2back.entity.UserEntity;
import org.example.lab2back.security.JwtService;
import org.example.lab2back.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;
    private final AuthenticationManager authenticationManager;

    public AuthController(UserService userService, JwtService jwtService,
                          UserDetailsService userDetailsService, AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@Valid @RequestBody UserCreateDto dto) {
        UserEntity user = userService.createUser(dto.getUsername(), dto.getPassword(), dto.getCurrency());
        String token = jwtService.generateToken(
                userDetailsService.loadUserByUsername(user.getUsername()),
                user.getId(),
                user.getRole()
        );
        return ResponseEntity.ok(new AuthResponse(token, user.getId(), user.getUsername(), user.getRole()));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody AuthRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );
        UserEntity user = userService.findByUsername(request.getUsername());
        String token = jwtService.generateToken(
                userDetailsService.loadUserByUsername(user.getUsername()),
                user.getId(),
                user.getRole()
        );
        return ResponseEntity.ok(new AuthResponse(token, user.getId(), user.getUsername(), user.getRole()));
    }
}