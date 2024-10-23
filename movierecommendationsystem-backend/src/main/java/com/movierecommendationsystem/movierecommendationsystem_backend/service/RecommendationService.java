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
import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;
import java.util.Collections;

import com.movierecommendationsystem.movierecommendationsystem_backend.dto.InteractionDto;
import com.movierecommendationsystem.movierecommendationsystem_backend.entity.Interaction;
import com.movierecommendationsystem.movierecommendationsystem_backend.entity.Media;
import com.movierecommendationsystem.movierecommendationsystem_backend.entity.Movie;
import com.movierecommendationsystem.movierecommendationsystem_backend.entity.TvShow;
import com.movierecommendationsystem.movierecommendationsystem_backend.entity.User;
import com.movierecommendationsystem.movierecommendationsystem_backend.repository.InteractionRepository;
import com.movierecommendationsystem.movierecommendationsystem_backend.repository.UserRepository;

@Service
public class RecommendationService {
    @Autowired
    private InteractionRepository interactionRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MovieService movieService;
    @Autowired
    private TvShowService tvShowService;

    private final String pythonUrl = "http://localhost:5000";

    public void trainRecommendationModel(){
        String url = pythonUrl + "/train";
        List<Interaction> interactions = interactionRepository.findAll();
        List<InteractionDto> interactionsDto = interactions.stream().map(interaction ->
            modelMapper.map(interaction,InteractionDto.class)).toList();
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.postForObject(url, interactionsDto, Void.class);
    }

    public List<Media> getRecommendations(Long userId) {
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
            List<Long> mediaIds = responseEntity.getBody();
            Optional<User> user = userRepository.findById(userId);
            List<Media> medias = new ArrayList<>();
            for(Long id : mediaIds){
                // Interaction interaction = interactionRepository.findByMediaId(id).get(0);
                // if(interaction.getMediaType().getValue().equalsIgnoreCase("movie")){
                //     MovieDto movieDto = movieService.getMovieDetails(id);
                //     medias.add(movieDto);
                // }
                // else if(interaction.getMediaType().getValue().equalsIgnoreCase("tv show")){
                //     TvShowDto tvShowDto = tvShowService.getTvShowDetails(id);
                //     medias.add(tvShowDto);
                // }
                try{
                    Movie movieDto = movieService.getMovieDetails(id);
                    medias.add(movieDto);
                }
                catch(Exception e){
                    TvShow tvShowDto = tvShowService.getTvShowDetails(id);
                    medias.add(tvShowDto);
                }
            }
            return medias;
        } 
        catch (HttpClientErrorException e) {
            // Log the error and return an empty list if something goes wrong
            System.err.println("Error during HTTP request: " + e.getMessage());
            return Collections.emptyList();  // Or handle it as needed
        }
    }
    

    
}
