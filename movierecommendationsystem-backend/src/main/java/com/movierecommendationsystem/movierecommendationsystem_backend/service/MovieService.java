package com.movierecommendationsystem.movierecommendationsystem_backend.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.movierecommendationsystem.movierecommendationsystem_backend.dto.GenreResponse;
import com.movierecommendationsystem.movierecommendationsystem_backend.dto.TMDbResponse;
import com.movierecommendationsystem.movierecommendationsystem_backend.entity.Genre;
import com.movierecommendationsystem.movierecommendationsystem_backend.entity.Movie;

import java.util.List;

@Service
public class MovieService {
    @Value("${tmdb.api.key}")
    private String apiKey;
    private static final String POPULAR_MOVIES_URL = "https://api.themoviedb.org/3/movie/popular?api_key=%s&page=%d";
    private static final String SEARCH_MOVIE_URL = "https://api.themoviedb.org/3/search/movie?api_key=%s&query=%s&page=%d";
    private static final String MOVIE_DETAILS_URL = "https://api.themoviedb.org/3/movie/%d?api_key=%s";
    private static final String MOVIE_GENRES_URL = "https://api.themoviedb.org/3/genre/movie/list?api_key=%s";
    
    private final RestTemplate restTemplate;

    public MovieService(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    public List<Movie> getPopularMovies(int page){
        String url = String.format(POPULAR_MOVIES_URL,apiKey,page);
        TMDbResponse response = restTemplate.getForObject(url,TMDbResponse.class);
        return response.getMovies();
    }

    public List<Movie> searchMovies(String query, int page) {
        String url = String.format(SEARCH_MOVIE_URL, apiKey, query, page);
        TMDbResponse response = restTemplate.getForObject(url, TMDbResponse.class);
        return response.getMovies();
    }

    public Movie getMovieDetails(Long movieId) {
        String url = String.format(MOVIE_DETAILS_URL, movieId, apiKey);
        return restTemplate.getForObject(url, Movie.class);
    }

    public List<Genre> getMovieGenres() {
        String url = String.format(MOVIE_GENRES_URL, apiKey);
        GenreResponse response = restTemplate.getForObject(url, GenreResponse.class);
        return response.getGenres();
    }
}
