package com.movierecommendationsystem.movierecommendationsystem_backend;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.movierecommendationsystem.movierecommendationsystem_backend.dto.RegistrationRequest;
import com.movierecommendationsystem.movierecommendationsystem_backend.dto.UserDto;
import com.movierecommendationsystem.movierecommendationsystem_backend.entity.User;
import com.movierecommendationsystem.movierecommendationsystem_backend.repository.UserRepository;
import com.movierecommendationsystem.movierecommendationsystem_backend.service.UserService;

@SpringBootTest
public class UserTest {
    @Mock
    private UserRepository userRepository;
    @Mock
    private ModelMapper modelMapper;
    @Mock
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @InjectMocks
    private UserService userService;

    private UserDto userDto;
    private User user;
    private RegistrationRequest registrationRequest;

    @BeforeEach
    void setup(){
        user = new User();
        user.setUsername("test");
        user.setPassword("test");
        user.setEmail("test@gmail.com");
        registrationRequest.setUsername("test");
        registrationRequest.setPassword("test");
        registrationRequest.setConfirmPassword("test");
        registrationRequest.setEmail("test@gmail.com");
    }

    @Test
    public void registerWithSuccessTest(){
        when(userRepository.findByUsername(anyString())).thenReturn(Optional.empty());
        when(userRepository.findByEmail(anyString())).thenReturn(Optional.empty());
        when(userService.registerUser(registrationRequest)).thenReturn(userDto);
        when(bCryptPasswordEncoder.encode(anyString())).thenReturn("encoded");
        when(userRepository.save(any(User.class))).thenReturn(user);
        when(modelMapper.map(any(User.class), eq(UserDto.class))).thenReturn(new UserDto());
        UserDto result = userService.registerUser(registrationRequest);
        assertNotNull(result);
        assertEquals(user.getUsername(), result.getUsername());
        verify(userRepository, times(1)).save(any(User.class));
        verify(bCryptPasswordEncoder,times(1)).encode(registrationRequest.getPassword());
    }
    
}
