package com.movierecommendationsystem.movierecommendationsystem_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class LoginResponse {
    private String response;
    private String token;
    private String role;
}
