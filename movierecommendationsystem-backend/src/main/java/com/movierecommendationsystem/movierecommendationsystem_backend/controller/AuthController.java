package com.movierecommendationsystem.movierecommendationsystem_backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.movierecommendationsystem.movierecommendationsystem_backend.dto.LoginDto;
import com.movierecommendationsystem.movierecommendationsystem_backend.dto.LoginResponse;
import com.movierecommendationsystem.movierecommendationsystem_backend.service.AuthService;

@RestController
@RequestMapping(path = "/auth")
public class AuthController {
    @Autowired
    private AuthService authService;
    
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginDto login){
        LoginResponse loginResponse = authService.login(login);
        return ResponseEntity.ok(loginResponse);
    }
    
}
