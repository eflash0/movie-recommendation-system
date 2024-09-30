package com.movierecommendationsystem.movierecommendationsystem_backend.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RegistrationRequest {
    @Column(unique = true,nullable = false)
    @NotEmpty
    String username;
    @Email(message = "invalid email")
    String email;
    @NonNull
    @NotEmpty
    String password;
    @NonNull
    @NotEmpty
    String confirmPassword;
}
