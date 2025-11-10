package org.example.lab2back.dto;

public class AuthResponse {
    private String token;
    private Long userId;
    private String username;
    private String role;

    public AuthResponse(String token, Long userId, String username, String role) {
        this.token = token;
        this.userId = userId;
        this.username = username;
        this.role = role;
    }

    // getters
    public String getToken() { return token; }
    public Long getUserId() { return userId; }
    public String getUsername() { return username; }
    public String getRole() { return role; }
}