package com.movierecommendationsystem.movierecommendationsystem_backend.dto;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.movierecommendationsystem.movierecommendationsystem_backend.entity.Movie;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TMDbMovieResponse {
    private List<Movie> results;
    @JsonProperty("total_pages")
    private int totalPage;
}
