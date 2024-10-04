package com.movierecommendationsystem.movierecommendationsystem_backend.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.movierecommendationsystem.movierecommendationsystem_backend.dto.LoginDto;
import com.movierecommendationsystem.movierecommendationsystem_backend.dto.LoginResponse;
import com.movierecommendationsystem.movierecommendationsystem_backend.entity.User;
import com.movierecommendationsystem.movierecommendationsystem_backend.repository.UserRepository;
import com.movierecommendationsystem.movierecommendationsystem_backend.security.JwtUtil;

@Service
public class AuthService {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private UserService userService;

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
                new UsernamePasswordAuthenticationToken(user.get().getUsername(), login.getPassword())
            );
            String role = authentication.getAuthorities().stream()
            .map(authority -> authority.getAuthority())
            .findFirst().orElse("USER");
            String token = jwtUtil.generateToken(user.get().getUsername(), role);
            return new LoginResponse("login with success",token,role);
        }
        catch(AuthenticationException e){
            throw new RuntimeException("login failed " + e.getMessage());
        }
    }

    public LoginResponse oauth2Login(OAuth2User oauth2User) {
        try {
            String email = oauth2User.getAttribute("email");
            Optional<User> user = userRepository.findByEmail(email);
            if (!user.isPresent()) {
                User newUser = new User();
                newUser.setEmail(email);
                newUser.setUsername(oauth2User.getAttribute("name"));
                userRepository.save(newUser);
                user = Optional.of(newUser);
            }
            UserDetails userDetails = userService.loadUserByUsername(user.get().getUsername());
            String role = userDetails.getAuthorities().stream()
                    .map(authority -> authority.getAuthority())
                    .findFirst().orElse("USER");
            String token = jwtUtil.generateToken(user.get().getUsername(), role);

            return new LoginResponse("OAuth2 login successful", token, role);
        } catch (Exception e) {
            throw new RuntimeException("OAuth2 login failed: " + e.getMessage());
        }
    }
}
