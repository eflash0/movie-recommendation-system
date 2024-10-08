package com.movierecommendationsystem.movierecommendationsystem_backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.movierecommendationsystem.movierecommendationsystem_backend.dto.RegistrationRequest;
import com.movierecommendationsystem.movierecommendationsystem_backend.dto.UserDto;
import com.movierecommendationsystem.movierecommendationsystem_backend.entity.User;
import com.movierecommendationsystem.movierecommendationsystem_backend.service.UserService;

@RestController
@RequestMapping(path = "/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<UserDto>> getUsers(){
        List<UserDto> users = userService.findUsers();
        return ResponseEntity.ok(users);
    }
    @PostMapping("/signup")
    public ResponseEntity<UserDto> register(@RequestBody RegistrationRequest registrationRequest){
        UserDto user = userService.registerUser(registrationRequest);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/findByUsername")
    public ResponseEntity<UserDto> findByUsername(@RequestParam String username){
        UserDto userDto = userService.findByUsername(username);
        return ResponseEntity.ok(userDto);
    }

    @GetMapping("/findByEmail")
    public ResponseEntity<UserDto> findByEmail(@RequestParam String email){
        UserDto userDto = userService.findByEmail(email);
        return ResponseEntity.ok(userDto);
    }
}
