package com.movierecommendationsystem.movierecommendationsystem_backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

import com.movierecommendationsystem.movierecommendationsystem_backend.dto.LoginDto;
import com.movierecommendationsystem.movierecommendationsystem_backend.dto.LoginResponse;

@Service
public class AuthService {
    @Autowired
    private AuthenticationManager authenticationManager;
    public LoginResponse login(LoginDto login){
        try{
            Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(login.getUsername(), login.getPassword())
            );
            String role = authentication.getAuthorities().stream()
            .map(authority -> authority.getAuthority())
            .findFirst().orElse("USER");
            return new LoginResponse("login with success",role);
        }
        catch(AuthenticationException e){
            throw new RuntimeException("login failed" + e.getMessage());
        }
    }
}
