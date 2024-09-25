package com.movierecommendationsystem.movierecommendationsystem_backend.dto;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TMDbResponse {
    private List<MovieDto> results;
    @JsonProperty("total_pages")
    private int totalPage;
}
