package com.movierecommendationsystem.movierecommendationsystem_backend.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

import com.movierecommendationsystem.movierecommendationsystem_backend.dto.LoginDto;
import com.movierecommendationsystem.movierecommendationsystem_backend.dto.LoginResponse;
import com.movierecommendationsystem.movierecommendationsystem_backend.entity.User;
import com.movierecommendationsystem.movierecommendationsystem_backend.repository.UserRepository;

@Service
public class AuthService {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserRepository userRepository;
    public LoginResponse login(LoginDto login){
        try{
            Optional<User> user = userRepository.findByEmail(login.getUsername());
            if (!user.isPresent()) {
                user = userRepository.findByUsername(login.getUsername());
            }
            if (!user.isPresent()) {
                throw new RuntimeException("User not found with username or email: " + login.getUsername());
            }
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
