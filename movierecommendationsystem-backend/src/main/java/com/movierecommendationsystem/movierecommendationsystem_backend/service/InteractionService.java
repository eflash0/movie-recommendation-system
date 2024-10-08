package com.movierecommendationsystem.movierecommendationsystem_backend.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.movierecommendationsystem.movierecommendationsystem_backend.dto.InteractionDto;
import com.movierecommendationsystem.movierecommendationsystem_backend.dto.MovieDto;
import com.movierecommendationsystem.movierecommendationsystem_backend.entity.Interaction;
import com.movierecommendationsystem.movierecommendationsystem_backend.entity.User;
import com.movierecommendationsystem.movierecommendationsystem_backend.repository.InteractionRepository;
import com.movierecommendationsystem.movierecommendationsystem_backend.repository.UserRepository;

@Service
public class InteractionService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private InteractionRepository interactionRepository;
    @Autowired
    private MovieService movieService;
    @Autowired
    private ModelMapper modelMapper;

    public InteractionDto rateMovie(Long userId,Long movieId,double rating){
        User user = userRepository.findById(userId).orElseThrow(() -> 
        new IllegalArgumentException("user not found")); 
        Interaction interaction = interactionRepository.findByUserAndMovieId(user, movieId).orElse(new Interaction());
        interaction.setUser(user);
        interaction.setMovieId(movieId);
        interaction.setRating(rating);
        Interaction savedInteraction = interactionRepository.save(interaction);  
        return modelMapper.map(savedInteraction, InteractionDto.class);
    }

    public InteractionDto addToWatchList(Long userId,Long movieId){
        User user = userRepository.findById(userId).orElseThrow(() -> 
        new IllegalArgumentException("user not found")); 
        Interaction interaction = interactionRepository.findByUserAndMovieId(user, movieId).orElse(new Interaction());
        interaction.setUser(user);
        interaction.setMovieId(movieId);
        interaction.setWatchList(true);
        Interaction savedInteraction = interactionRepository.save(interaction);  
        return modelMapper.map(savedInteraction, InteractionDto.class);
    }

    public void removeFromWatchList(Long userId,Long movieId){
        User user = userRepository.findById(userId).orElseThrow(() -> 
        new IllegalArgumentException("user not found")); 
        Interaction interaction = interactionRepository.findByUserAndMovieId(user, movieId).orElse(new Interaction());
        interaction.setUser(user);
        interaction.setMovieId(movieId);
        interaction.setWatchList(false);
        interactionRepository.deleteById(interaction.getInteractionId());
    }

    public InteractionDto addToFavorite(Long userId,Long movieId){
        User user = userRepository.findById(userId).orElseThrow(() -> 
        new IllegalArgumentException("user not found")); 
        Interaction interaction = interactionRepository.findByUserAndMovieId(user, movieId).orElse(new Interaction());
        interaction.setUser(user);
        interaction.setMovieId(movieId);
        interaction.setFavorite(true);
        Interaction savedInteraction = interactionRepository.save(interaction);  
        return modelMapper.map(savedInteraction, InteractionDto.class);
    }

    public List<MovieDto> getWatchListMovies(Long userId){
        User user = userRepository.findById(userId).orElseThrow(() -> 
        new IllegalArgumentException("user not found")); 
        List<Interaction> interactions = interactionRepository.findByUserAndIsWatchList(user,true);
        return interactions.stream().map(interaction -> movieService.getMovieDetails(interaction.getMovieId()))
        .toList();
    }

    public void removeFromFavorite(Long userId,Long movieId){
        User user = userRepository.findById(userId).orElseThrow(() -> 
        new IllegalArgumentException("user not found")); 
        Interaction interaction = interactionRepository.findByUserAndMovieId(user, movieId).orElse(new Interaction());
        interaction.setUser(user);
        interaction.setMovieId(movieId);
        interaction.setFavorite(false);
        interactionRepository.deleteById(interaction.getInteractionId());
    }

    public List<MovieDto> getFavoriteMovies(Long userId){
        User user = userRepository.findById(userId).orElseThrow(() -> 
        new IllegalArgumentException("user not found")); 
        List<Interaction> interactions = interactionRepository.findByUserAndIsFavorite(user,true);
        return interactions.stream().map(interaction -> movieService.getMovieDetails(interaction.getMovieId()))
        .toList();
    }

    public InteractionDto findInteraction(Long userId,Long movieId){
        User user = userRepository.findById(userId).orElseThrow(() -> 
        new IllegalArgumentException("user not found")); 
        Interaction interaction = interactionRepository.findByUserAndMovieId(user, movieId).orElseThrow(
            () -> new IllegalArgumentException("no interaction between the user and the movie")
        );
        return modelMapper.map(interaction, InteractionDto.class);
    }
}
