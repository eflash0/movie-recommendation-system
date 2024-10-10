package com.movierecommendationsystem.movierecommendationsystem_backend.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.movierecommendationsystem.movierecommendationsystem_backend.entity.Genre;

public class TvShowDto {
    @JsonProperty("id")
    private Long tvShowId;

    @JsonProperty("original_name")
    private String name;

    @JsonProperty("overview")
    private String overview;

    @JsonProperty("first_air_date")
    private String releaseDate;

    @JsonProperty("poster_path")
    private String posterPath;

    @JsonProperty("popularity")
    private Double popularity;

    @JsonProperty("vote_average")
    private Double voteAverage;

    @JsonProperty("genres")
    private List<Genre> genres;
}
