package com.movierecommendationsystem.movierecommendationsystem_backend.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.movierecommendationsystem.movierecommendationsystem_backend.dto.SeasonDto;

public class TvShow extends Media {
    @JsonProperty("name")
    private String name;

    @JsonProperty("first_air_date")
    private String firstAirDate;

    @JsonProperty("seasons")
    private List<SeasonDto> seasons;
}
