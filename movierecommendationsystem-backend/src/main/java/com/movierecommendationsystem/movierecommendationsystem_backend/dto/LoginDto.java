package com.movierecommendationsystem.movierecommendationsystem_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class LoginDto {
    private String username;
    private String password;
}
