package com.movierecommendationsystem.movierecommendationsystem_backend.service;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.movierecommendationsystem.movierecommendationsystem_backend.dto.RegistrationRequest;
import com.movierecommendationsystem.movierecommendationsystem_backend.dto.UserDto;
import com.movierecommendationsystem.movierecommendationsystem_backend.entity.Role;
import com.movierecommendationsystem.movierecommendationsystem_backend.entity.User;
import com.movierecommendationsystem.movierecommendationsystem_backend.repository.UserRepository;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username.trim()).orElseThrow(() -> 
        new UsernameNotFoundException("user not found"));
    }

    public UserDto findByUsername(String username){
        User user = userRepository.findByUsername(username).orElseThrow(
            () -> new IllegalArgumentException("user not found with username"+username)
        );
        return modelMapper.map(user, UserDto.class);
    }

    public UserDto findByEmail(String email){
        User user = userRepository.findByUsername(email).orElseThrow(
            () -> new IllegalArgumentException("user not found with email"+email)
        );
        return modelMapper.map(user, UserDto.class);
    }

    public UserDto registerUser(RegistrationRequest registrationRequest){
        Optional<User> existingUser = userRepository.findByUsername(registrationRequest.getUsername());
        if(existingUser.isPresent()){
            throw new IllegalArgumentException("the username already exists");
        }
        existingUser = userRepository.findByEmail(registrationRequest.getEmail());
        if(existingUser.isPresent()){
            throw new IllegalArgumentException("the email already exists");
        }
        if(!registrationRequest.getPassword().equals(registrationRequest.getConfirmPassword())){
            throw new IllegalArgumentException("the password and the confirm password must be equals");
        }
        User user = new User();
        user.setUsername(registrationRequest.getUsername());
        user.setEmail(registrationRequest.getEmail());
        user.setRole(Role.USER);
        user.setPassword(bCryptPasswordEncoder.encode(registrationRequest.getPassword()));
        User savedUser = userRepository.save(user);
        return modelMapper.map(savedUser,UserDto.class);
    }

}
