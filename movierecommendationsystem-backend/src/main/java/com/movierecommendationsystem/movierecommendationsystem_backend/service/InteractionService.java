package com.movierecommendationsystem.movierecommendationsystem_backend.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.movierecommendationsystem.movierecommendationsystem_backend.dto.InteractionDto;
import com.movierecommendationsystem.movierecommendationsystem_backend.dto.Media;
import com.movierecommendationsystem.movierecommendationsystem_backend.dto.MovieDto;
import com.movierecommendationsystem.movierecommendationsystem_backend.dto.TvShowDto;
import com.movierecommendationsystem.movierecommendationsystem_backend.entity.Interaction;
import com.movierecommendationsystem.movierecommendationsystem_backend.entity.MediaType;
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
    private TvShowService tvShowService;
    @Autowired
    private ModelMapper modelMapper;

    public List<InteractionDto> getInteractions(){
        List<Interaction> interactions = interactionRepository.findAll();
        return interactions.stream().map(interaction -> modelMapper
        .map(interaction, InteractionDto.class)).toList();
    } 

    public InteractionDto rateMedia(Long userId,Long mediaId,double rating,String type){
        User user = userRepository.findById(userId).orElseThrow(() -> 
        new IllegalArgumentException("user not found")); 
        Interaction interaction = interactionRepository.findByUserAndMediaId(user, mediaId).orElse(new Interaction());
        interaction.setUser(user);
        interaction.setMediaId(mediaId);
        interaction.setRating(rating);
        interaction.setMediaType(MediaType.fromValue(type));
        Interaction savedInteraction = interactionRepository.save(interaction);  
        return modelMapper.map(savedInteraction, InteractionDto.class);
    }

    public InteractionDto addToWatchList(Long userId, Long mediaId, String type) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        Interaction interaction = interactionRepository.findByUserAndMediaId(user, mediaId)
                .orElse(new Interaction());
    
        interaction.setUser(user);
        interaction.setMediaId(mediaId);
        interaction.setWatchList(true);
        interaction.setMediaType(MediaType.fromValue(type));
        Interaction savedInteraction = interactionRepository.save(interaction);
        return modelMapper.map(savedInteraction, InteractionDto.class);
    }

    public InteractionDto removeFromWatchList(Long userId,Long mediaId){
        User user = userRepository.findById(userId).orElseThrow(() -> 
        new IllegalArgumentException("user not found")); 
        Interaction interaction = interactionRepository.findByUserAndMediaId(user, mediaId).orElse(new Interaction());
        interaction.setUser(user);
        interaction.setMediaId(mediaId);
        interaction.setWatchList(false);
        Interaction savedInteraction = interactionRepository.save(interaction);  
        return modelMapper.map(savedInteraction, InteractionDto.class);
    }

    public InteractionDto addToFavorite(Long userId, Long mediaId, String type) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        Interaction interaction = interactionRepository.findByUserAndMediaId(user, mediaId)
                .orElse(new Interaction());
        interaction.setUser(user);
        interaction.setMediaId(mediaId);
        interaction.setFavorite(true);
        interaction.setMediaType(MediaType.fromValue(type));
        Interaction savedInteraction = interactionRepository.save(interaction);
        return modelMapper.map(savedInteraction, InteractionDto.class);
    }

    public List<Media> getWatchList(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        List<Interaction> interactions = interactionRepository.findByUserAndIsWatchList(user, true);
        List<Media> mediaList = new ArrayList<>();
        for(Interaction interaction : interactions) {
            if(!interaction.isWatchList()) continue;
            if("movie".equalsIgnoreCase(interaction.getMediaType().getValue())) {
                MovieDto movieDto = movieService.getMovieDetails(interaction.getMediaId());
                mediaList.add(movieDto);
            } 
            else if("tv show".equalsIgnoreCase(interaction.getMediaType().getValue())) {
                TvShowDto tvShowDto = tvShowService.getTvShowDetails(interaction.getMediaId());
                mediaList.add(tvShowDto);
            }
        }
        return mediaList;
    }

    public InteractionDto removeFromFavorite(Long userId,Long mediaId){
        User user = userRepository.findById(userId).orElseThrow(() -> 
        new IllegalArgumentException("user not found")); 
        Interaction interaction = interactionRepository.findByUserAndMediaId(user, mediaId).orElse(new Interaction());
        interaction.setUser(user);
        interaction.setMediaId(mediaId);
        interaction.setFavorite(false);
        Interaction savedInteraction = interactionRepository.save(interaction);  
        return modelMapper.map(savedInteraction, InteractionDto.class);
    }

    public List<Media> getFavoriteMedias(Long userId){
        User user = userRepository.findById(userId).orElseThrow(() -> 
        new IllegalArgumentException("user not found")); 
        List<Interaction> interactions = interactionRepository.findByUserAndIsFavorite(user, true);
        List<Media> mediaList = new ArrayList<>();
        for(Interaction interaction : interactions) {
            if(!interaction.isFavorite()) continue;
            if("movie".equalsIgnoreCase(interaction.getMediaType().getValue())) {
                MovieDto movieDto = movieService.getMovieDetails(interaction.getMediaId());
                mediaList.add(movieDto);
            } 
            else if("tv show".equalsIgnoreCase(interaction.getMediaType().getValue())) {
                TvShowDto tvShowDto = tvShowService.getTvShowDetails(interaction.getMediaId());
                mediaList.add(tvShowDto);
            }
        }
        return mediaList;
    }

    public InteractionDto findInteraction(Long userId,Long mediaId){
        User user = userRepository.findById(userId).orElseThrow(() -> 
        new IllegalArgumentException("user not found")); 
        Interaction interaction = interactionRepository.findByUserAndMediaId(user, mediaId).orElseGet(
            () -> {
                Interaction newInteraction = new Interaction();
                newInteraction.setUser(user);
                newInteraction.setMediaId(mediaId);
                newInteraction.setWatchList(false);
                newInteraction.setFavorite(false);
                return interactionRepository.save(newInteraction);
            }
        );
        return modelMapper.map(interaction, InteractionDto.class);
    }
}