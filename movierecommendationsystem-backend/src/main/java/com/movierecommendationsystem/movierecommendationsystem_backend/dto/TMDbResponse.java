package com.movierecommendationsystem.movierecommendationsystem_backend.dto;
import java.util.List;

import com.movierecommendationsystem.movierecommendationsystem_backend.entity.Movie;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TMDbResponse {
    private List<Movie> movies;
}
