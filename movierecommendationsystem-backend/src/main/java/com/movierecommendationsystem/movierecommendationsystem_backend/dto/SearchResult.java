package com.movierecommendationsystem.movierecommendationsystem_backend.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.movierecommendationsystem.movierecommendationsystem_backend.entity.Genre;

public class SearchResult {
@JsonProperty("id")
    private Long id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("title")
    private String title;

    @JsonProperty("overview")
    private String overview;

    @JsonProperty("release_date")
    private String releaseDate;

    @JsonProperty("first_air_date")
    private String firstAirDate;

    @JsonProperty("poster_path")
    private String posterPath;

    @JsonProperty("popularity")
    private Double popularity;

    @JsonProperty("vote_average")
    private Double voteAverage;

    @JsonProperty("genres")
    private List<Genre> genres;
}
