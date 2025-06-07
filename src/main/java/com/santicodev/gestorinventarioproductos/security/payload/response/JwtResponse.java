package com.santicodev.gestorinventarioproductos.security.payload.response;

import lombok.Data;

import java.util.List;

@Data
public class JwtResponse {
    private String token;
    private String type = "Bearer"; // Tipo de token, por convención "Bearer"
    private Long id;
    private String username;
    private List<String> roles; // Lista de roles del usuario

    public JwtResponse(String accessToken, Long id, String username, List<String> roles) {
        this.token = accessToken;
        this.id = id;
        this.username = username;
        this.roles = roles;
    }
}
