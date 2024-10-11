package com.movierecommendationsystem.movierecommendationsystem_backend.dto;

import com.movierecommendationsystem.movierecommendationsystem_backend.entity.MediaType;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class InteractionDto {
    private Long interactionId;
    private UserDto user;
    private Long mediaId;
    private double rating;
    private boolean isFavorite;
    private boolean isWatchList;
    @Enumerated(EnumType.STRING)
    private MediaType mediaType;
}

