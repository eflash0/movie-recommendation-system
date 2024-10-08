package com.movierecommendationsystem.movierecommendationsystem_backend.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class InteractionDto {
    private Long interactionId;
    private UserDto user;
    private Long movieId;
    private double rating;
    private boolean isFavorite;
    private boolean isWatchList;
}

