package com.movierecommendationsystem.movierecommendationsystem_backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.movierecommendationsystem.movierecommendationsystem_backend.dto.RegistrationRequest;
import com.movierecommendationsystem.movierecommendationsystem_backend.dto.UserDto;
import com.movierecommendationsystem.movierecommendationsystem_backend.service.UserService;

@RestController
@RequestMapping(path = "/users")
public class UserController {
    @Autowired
    private UserService userService;
    public ResponseEntity<UserDto> register(@RequestBody RegistrationRequest registrationRequest){
        UserDto user = userService.registerUser(registrationRequest);
        return ResponseEntity.ok(user);
    }
}
