package com.movierecommendationsystem.movierecommendationsystem_backend.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.movierecommendationsystem.movierecommendationsystem_backend.entity.MovieVideo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TMDbVideosResponse {
    @JsonProperty("results")
    private List<MovieVideo> results;
}
