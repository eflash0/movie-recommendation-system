package com.movierecommendationsystem.movierecommendationsystem_backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.movierecommendationsystem.movierecommendationsystem_backend.dto.LoginDto;
import com.movierecommendationsystem.movierecommendationsystem_backend.dto.LoginResponse;
import com.movierecommendationsystem.movierecommendationsystem_backend.security.JwtUtil;
import com.movierecommendationsystem.movierecommendationsystem_backend.service.AuthService;

@RestController
@RequestMapping(path = "/auth")
public class AuthController {
    @Autowired
    private AuthService authService;
    @Autowired
    private JwtUtil jwtUtil;
    
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginDto login){
        LoginResponse loginResponse = authService.login(login);
        return ResponseEntity.ok(loginResponse);
    }

    @GetMapping("/oauth2/success")
    public ResponseEntity<LoginResponse> oauth2Login(@RequestParam("code") String code,OAuth2User oauth2User) {
        LoginResponse loginResponse = authService.oauth2Login(oauth2User);
        return ResponseEntity.ok(loginResponse);
    }

    @GetMapping("/validate-token")
    public ResponseEntity<Boolean> validateToken(@RequestParam String token) {
        boolean isValid = jwtUtil.validateToken(token);
        return ResponseEntity.ok(isValid);
    } 
}
