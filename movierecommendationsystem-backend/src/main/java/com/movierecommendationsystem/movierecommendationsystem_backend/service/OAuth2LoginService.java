package com.movierecommendationsystem.movierecommendationsystem_backend.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Service;

import com.movierecommendationsystem.movierecommendationsystem_backend.dto.LoginResponse;
import com.movierecommendationsystem.movierecommendationsystem_backend.entity.Role;
import com.movierecommendationsystem.movierecommendationsystem_backend.entity.User;
import com.movierecommendationsystem.movierecommendationsystem_backend.repository.UserRepository;
import com.movierecommendationsystem.movierecommendationsystem_backend.security.JwtUtil;

@Service
public class OAuth2LoginService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JwtUtil jwtUtil;
    public LoginResponse oauth2Login(OAuth2AuthenticationToken oAuthToken) {
        try {
            String username = oAuthToken.getPrincipal().getAttribute("name");
            String email = oAuthToken.getPrincipal().getAttribute("email");
            Optional<User> user = userRepository.findByEmail(email);
            if (!user.isPresent()) {
                User newUser = new User();
                newUser.setEmail(email);
                newUser.setUsername(username);
                newUser.setRole(Role.USER);
                userRepository.save(newUser);
                user = Optional.of(newUser);
            }
            String token = jwtUtil.generateToken(username, "USER");

            return new LoginResponse("OAuth2 login successful", token, "USER");
        } catch (Exception e) {
            throw new RuntimeException("OAuth2 login failed: " + e.getMessage());
        }
    }
}
