package com.movierecommendationsystem.movierecommendationsystem_backend.dto;

import java.util.List;

import com.movierecommendationsystem.movierecommendationsystem_backend.entity.Genre;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class GenreResponse {
    List<Genre> genres;
}
