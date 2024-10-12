package com.movierecommendationsystem.movierecommendationsystem_backend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Episode {
    @JsonProperty("id")
    private Long id;
    @JsonProperty("episode_number")
    private int episodeNumber;
    @JsonProperty("name")
    private String name;
    @JsonProperty("overview")
    private String overview;
    @JsonProperty("still_path")
    private String stillPath;
}
