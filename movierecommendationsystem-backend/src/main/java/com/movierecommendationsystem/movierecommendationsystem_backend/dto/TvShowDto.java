package com.movierecommendationsystem.movierecommendationsystem_backend.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TvShowDto extends Media {
    @JsonProperty("name")
    private String name;

    @JsonProperty("first_air_date")
    private String firstAirDate;

    @JsonProperty("seasons")
    private List<SeasonDto> seasons;
}
