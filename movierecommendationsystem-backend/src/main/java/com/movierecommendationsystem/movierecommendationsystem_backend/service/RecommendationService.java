package com.movierecommendationsystem.movierecommendationsystem_backend.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Collections;

import com.movierecommendationsystem.movierecommendationsystem_backend.dto.InteractionDto;
import com.movierecommendationsystem.movierecommendationsystem_backend.dto.MovieDto;
import com.movierecommendationsystem.movierecommendationsystem_backend.entity.Interaction;
import com.movierecommendationsystem.movierecommendationsystem_backend.repository.InteractionRepository;

@Service
public class RecommendationService {
    @Autowired
    private InteractionRepository interactionRepository;
    @Autowired
    private ModelMapper modelMapper;

    private final String pythonUrl = "http://localhost:5000";

    public void trainRecommendationModel(){
        String url = pythonUrl + "/train";
        List<Interaction> interactions = interactionRepository.findAll();
        List<InteractionDto> interactionsDto = interactions.stream().map(interaction ->
            modelMapper.map(interaction,InteractionDto.class)).toList();
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.postForObject(url, interactionsDto, Void.class);
    }

    public List<Long> getRecommendations(Long userId) {
        String url = pythonUrl + "/recommend";
        RestTemplate restTemplate = new RestTemplate();
    
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        
        Map<String, Long> requestBody = new HashMap<>();
        requestBody.put("userId", userId);
    
        HttpEntity<Map<String, Long>> requestEntity = new HttpEntity<>(requestBody, headers);
    
        try {
            // Expecting a list of Long values (movie IDs) from the Python API
            ResponseEntity<List<Long>> responseEntity = restTemplate.exchange(
                url,
                HttpMethod.POST,
                requestEntity,
                new ParameterizedTypeReference<List<Long>>() {}
            );
    
            // Return the body which contains the list of movie IDs
            return responseEntity.getBody();
        } catch (HttpClientErrorException e) {
            // Log the error and return an empty list if something goes wrong
            System.err.println("Error during HTTP request: " + e.getMessage());
            return Collections.emptyList();  // Or handle it as needed
        }
    }
    

    
}
