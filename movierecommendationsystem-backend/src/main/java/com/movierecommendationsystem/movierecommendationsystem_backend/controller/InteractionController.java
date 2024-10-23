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

import com.movierecommendationsystem.movierecommendationsystem_backend.dto.InteractionDto;
import com.movierecommendationsystem.movierecommendationsystem_backend.dto.Media;
import com.movierecommendationsystem.movierecommendationsystem_backend.service.InteractionService;

@RestController
@RequestMapping(path = "/interactions")
public class InteractionController {
    @Autowired
    private InteractionService interactionService;

    @GetMapping
    public ResponseEntity<List<InteractionDto>> getInteractions(){
        List<InteractionDto> interactions = interactionService.getInteractions();
        return ResponseEntity.ok(interactions);
    }

    @GetMapping("/{userId}/{mediaId}")
    public ResponseEntity<InteractionDto> findInteraction(@PathVariable Long userId,
    @PathVariable Long mediaId){
        InteractionDto interaction = interactionService.findInteraction(userId, mediaId);
        return ResponseEntity.ok(interaction);
    }

    @PostMapping("/{userId}/{mediaId}/rating")
    public ResponseEntity<InteractionDto> rateMovie(@PathVariable Long userId,
    @PathVariable Long mediaId,@RequestParam double rating, @RequestParam String type){
        InteractionDto interaction = interactionService.rateMedia(userId,mediaId,rating,type);
        return ResponseEntity.ok(interaction);
    }    

    @GetMapping("{userId}/watchlist")
    public ResponseEntity<List<Media>> watchList(@PathVariable Long userId){
        List<Media> medias = interactionService.getWatchList(userId);
        return ResponseEntity.ok(medias);
    }

    @PostMapping("{userId}/{mediaId}/watchlist")
    public ResponseEntity<InteractionDto> addToWatchList(@PathVariable Long userId,
    @PathVariable Long mediaId, @RequestParam String type){
        InteractionDto interaction = interactionService.addToWatchList(userId, mediaId, type);
        return ResponseEntity.ok(interaction);
    }

    @DeleteMapping("{userId}/{mediaId}/watchlist")
    public ResponseEntity<InteractionDto> removeFromWatchList(@PathVariable Long userId,
    @PathVariable Long mediaId){
        InteractionDto interaction = interactionService.removeFromWatchList(userId, mediaId);
        return ResponseEntity.ok(interaction);
    }

    @PostMapping("{userId}/{mediaId}/favorite")
    public ResponseEntity<InteractionDto> addToFavorite(@PathVariable Long userId,
    @PathVariable Long mediaId, @RequestParam String type){
        InteractionDto interaction = interactionService.addToFavorite(userId, mediaId, type);
        return ResponseEntity.ok(interaction);
    }

    @GetMapping("{userId}/favorite")
    public ResponseEntity<List<Media>> favoriteMovies(@PathVariable Long userId){
        List<Media> medias = interactionService.getFavoriteMedias(userId);
        return ResponseEntity.ok(medias);
    }

    @DeleteMapping("{userId}/{mediaId}/favorite")
    public ResponseEntity<InteractionDto> removeFromFavorite(@PathVariable Long userId,
    @PathVariable Long mediaId){
        InteractionDto interaction = interactionService.removeFromFavorite(userId, mediaId);
        return ResponseEntity.ok(interaction);
    }

}
