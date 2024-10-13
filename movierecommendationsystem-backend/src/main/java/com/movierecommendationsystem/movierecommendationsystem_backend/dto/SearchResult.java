package com.movierecommendationsystem.movierecommendationsystem_backend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SearchResult {
@JsonProperty("id")
    private Long id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("title")
    private String title;

    @JsonProperty("release_date")
    private String releaseDate;

    @JsonProperty("first_air_date")
    private String firstAirDate;

    @JsonProperty("poster_path")
    private String posterPath;

}
