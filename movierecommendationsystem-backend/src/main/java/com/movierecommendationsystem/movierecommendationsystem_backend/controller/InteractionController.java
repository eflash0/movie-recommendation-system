package com.movierecommendationsystem.movierecommendationsystem_backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.movierecommendationsystem.movierecommendationsystem_backend.dto.MovieDto;
import com.movierecommendationsystem.movierecommendationsystem_backend.entity.Interaction;
import com.movierecommendationsystem.movierecommendationsystem_backend.service.InteractionService;

@RestController
@RequestMapping(path = "/interactions")
public class InteractionController {
    @Autowired
    private InteractionService interactionService;

    @GetMapping("/{userId}/{movieId}")
    public ResponseEntity<Interaction> findInteractionById(@PathVariable Long userId,
    @PathVariable Long movieId){
        Interaction interaction = interactionService.findInteraction(userId, movieId);
        return ResponseEntity.ok(interaction);
    }

    @PostMapping("/{userId}/{movieId}/rating")
    public ResponseEntity<Interaction> rateMovie(@PathVariable Long userId,
    @PathVariable Long movieId,@RequestParam double rating){
        Interaction interaction = interactionService.rateMovie(userId, movieId,rating);
        return ResponseEntity.ok(interaction);
    }    

    @GetMapping("{userId}/watchlist")
    public ResponseEntity<List<MovieDto>> watchLater(@PathVariable Long userId){
        List<MovieDto> movies = interactionService.getWatchListMovies(userId);
        return ResponseEntity.ok(movies);
    }

    @PostMapping("{userId}/{movieId}/watchlist")
    public ResponseEntity<Interaction> addToWatchList(@PathVariable Long userId,
    @PathVariable Long movieId){
        Interaction interaction = interactionService.addToWatchList(userId, movieId);
        return ResponseEntity.ok(interaction);
    }

    @DeleteMapping("{userId}/{movieId}/watchlist")
    public ResponseEntity<Void> removeFromWatchList(@PathVariable Long userId,
    @PathVariable Long movieId){
        interactionService.removeFromWatchList(userId, movieId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("{userId}/{movieId}/favorite")
    public ResponseEntity<Interaction> addToFavorite(@PathVariable Long userId,
    @PathVariable Long movieId){
        Interaction interaction = interactionService.addToFavorite(userId, movieId);
        return ResponseEntity.ok(interaction);
    }

    @GetMapping("{userId}/favorite")
    public ResponseEntity<List<MovieDto>> favoriteMovies(@PathVariable Long userId){
        List<MovieDto> movies = interactionService.getFavoriteMovies(userId);
        return ResponseEntity.ok(movies);
    }

    @DeleteMapping("{userId}/{movieId}/watchlist")
    public ResponseEntity<Void> removeFromFavorite(@PathVariable Long userId,
    @PathVariable Long movieId){
        interactionService.removeFromFavorite(userId, movieId);
        return ResponseEntity.noContent().build();
    }

}
