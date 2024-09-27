package com.movierecommendationsystem.movierecommendationsystem_backend.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MovieVideo {
    @JsonProperty("key")
    private String key;

    @JsonProperty("name")
    private String name;

    @JsonProperty("site")
    private String site;

    @JsonProperty("type")
    private String type;

    @JsonProperty("official")
    private boolean official;
    
    @JsonProperty("published_at")
    private String publishedAt;
}
