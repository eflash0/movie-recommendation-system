package com.movierecommendationsystem.movierecommendationsystem_backend.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.movierecommendationsystem.movierecommendationsystem_backend.entity.Genre;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MovieDto {

    @JsonProperty("id")
    private Long movieId;

    @JsonProperty("title")
    private String title;

    @JsonProperty("overview")
    private String overview;

    @JsonProperty("release_date")
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
