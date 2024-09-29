package com.movierecommendationsystem.movierecommendationsystem_backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public Interaction rateMovie(Long userId,Long movieId,double rating){
        User user = userRepository.findById(movieId).orElseThrow(() -> 
        new IllegalArgumentException("user not found")); 
        Interaction interaction = interactionRepository.findByUserAndMovieId(user.getUserId(), movieId).orElse(new Interaction());
        interaction.setUser(user);
        interaction.setMovieId(movieId);
        interaction.setRating(rating);
        return interactionRepository.save(interaction);  
    }

    public Interaction addToWatchList(Long userId,Long movieId){
        User user = userRepository.findById(movieId).orElseThrow(() -> 
        new IllegalArgumentException("user not found")); 
        Interaction interaction = interactionRepository.findByUserAndMovieId(user.getUserId(), movieId).orElse(new Interaction());
        interaction.setUser(user);
        interaction.setMovieId(movieId);
        interaction.setWatchLater(true);
        return interactionRepository.save(interaction);
    }

    public Interaction removeFromWatchList(Long userId,Long movieId){
        User user = userRepository.findById(userId).orElseThrow(() -> 
        new IllegalArgumentException("user not found")); 
        Interaction interaction = interactionRepository.findByUserAndMovieId(user.getUserId(), movieId).orElse(new Interaction());
        interaction.setUser(user);
        interaction.setMovieId(movieId);
        interaction.setWatchLater(false);
        return interactionRepository.save(interaction);
    }

    public Interaction addToFavorite(Long userId,Long movieId){
        User user = userRepository.findById(userId).orElseThrow(() -> 
        new IllegalArgumentException("user not found")); 
        Interaction interaction = interactionRepository.findByUserAndMovieId(user.getUserId(), movieId).orElse(new Interaction());
        interaction.setUser(user);
        interaction.setMovieId(movieId);
        interaction.setFavorite(true);
        return interactionRepository.save(interaction);
    }

    public List<MovieDto> getWatchLaterMovies(Long userId){
        User user = userRepository.findById(userId).orElseThrow(() -> 
        new IllegalArgumentException("user not found")); 
        List<Interaction> interactions = interactionRepository.findByUserIdAndIsWatchLaterTrue(user.getUserId());
        return interactions.stream().map(interaction -> movieService.getMovieDetails(interaction.getMovieId()))
        .toList();
    }

    public Interaction removeFromFavorite(Long userId,Long movieId){
        User user = userRepository.findById(userId).orElseThrow(() -> 
        new IllegalArgumentException("user not found")); 
        Interaction interaction = interactionRepository.findByUserAndMovieId(user.getUserId(), movieId).orElse(new Interaction());
        interaction.setUser(user);
        interaction.setMovieId(movieId);
        interaction.setFavorite(false);
        return interactionRepository.save(interaction);
    }

    public List<MovieDto> getFavoriteMovies(Long userId){
        User user = userRepository.findById(userId).orElseThrow(() -> 
        new IllegalArgumentException("user not found")); 
        List<Interaction> interactions = interactionRepository.findByUserIdAndIsFavorite(user.getUserId());
        return interactions.stream().map(interaction -> movieService.getMovieDetails(interaction.getMovieId()))
        .toList();
    }

    public Interaction findInteraction(Long userId,Long movieId){
        User user = userRepository.findById(userId).orElseThrow(() -> 
        new IllegalArgumentException("user not found")); 
        Optional<Interaction> interaction = interactionRepository.findByUserAndMovieId(user.getUserId(), movieId);
        return interaction.get();
    }
}
