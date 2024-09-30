package com.movierecommendationsystem.movierecommendationsystem_backend.dto;

import com.movierecommendationsystem.movierecommendationsystem_backend.entity.Role;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserDto {
    private Long userId;
    @Column(nullable = false)
    private String username;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String password;
    private Role role;
}
