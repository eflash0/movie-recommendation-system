package com.movierecommendationsystem.movierecommendationsystem_backend.security;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.movierecommendationsystem.movierecommendationsystem_backend.dto.LoginResponse;
import com.movierecommendationsystem.movierecommendationsystem_backend.service.AuthService;
import com.movierecommendationsystem.movierecommendationsystem_backend.service.OAuth2LoginService;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class CustomOAuth2SuccessHandler implements AuthenticationSuccessHandler {
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private OAuth2LoginService oAuth2LoginService;
    @Autowired 
    private ObjectMapper objectMapper;
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        OAuth2AuthenticationToken oauthToken = (OAuth2AuthenticationToken) authentication;
        LoginResponse loginResponse = oAuth2LoginService.oauth2Login(oauthToken);
        Map<String, Object> attributes = oauthToken.getPrincipal().getAttributes();
        String name = (String) attributes.get("name");
        String token = jwtUtil.generateToken(name,"USER");

        String loginResponseJson = objectMapper.writeValueAsString(loginResponse);
        String encodedLoginResponseJson = URLEncoder.encode(loginResponseJson, StandardCharsets.UTF_8.toString());

        String redirectUrl = "http://localhost:4200/login?response=" + encodedLoginResponseJson;
        response.sendRedirect(redirectUrl);
    }
}
