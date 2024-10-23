package com.movierecommendationsystem.movierecommendationsystem_backend.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Movie extends Media {

    @JsonProperty("title")
    private String title;

    @JsonProperty("release_date")
    private String releaseDate;
}
