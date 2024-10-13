package com.movierecommendationsystem.movierecommendationsystem_backend.dto;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class SeasonDto {
    @JsonProperty("id")
    private Long id;
    @JsonProperty("season_number")
    private int seasonNumber;
    @JsonProperty("name")
    private String name;
    @JsonProperty("overview")
    private String overview;
    @JsonProperty("poster_path")
    private String posterPath;
    @JsonProperty("air_date")
    private Date airDate;
    @JsonProperty("episodes")
    private List<Episode> episodes;
}
