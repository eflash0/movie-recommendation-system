package com.movierecommendationsystem.movierecommendationsystem_backend.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.movierecommendationsystem.movierecommendationsystem_backend.dto.GenreResponse;
import com.movierecommendationsystem.movierecommendationsystem_backend.dto.SearchResult;
import com.movierecommendationsystem.movierecommendationsystem_backend.dto.TMDbMovieResponse;
import com.movierecommendationsystem.movierecommendationsystem_backend.dto.TMDbVideosResponse;
import com.movierecommendationsystem.movierecommendationsystem_backend.dto.TMdBSearchResponse;
import com.movierecommendationsystem.movierecommendationsystem_backend.entity.Genre;
import com.movierecommendationsystem.movierecommendationsystem_backend.entity.MediaVideo;
import com.movierecommendationsystem.movierecommendationsystem_backend.entity.Movie;

import java.util.List;

@Service
public class MovieService {
    @Value("${tmdb.api.key}")
    private String apiKey;
    private static final String POPULAR_MOVIES_URL = "https://api.themoviedb.org/3/movie/popular?api_key=%s&page=%d";
    private static final String SEARCH_MEDIA_URL = "https://api.themoviedb.org/3/search/multi?api_key=%s&query=%s&page=%d";
    private static final String MOVIE_DETAILS_URL = "https://api.themoviedb.org/3/movie/%d?api_key=%s";
    private static final String MOVIE_GENRES_URL = "https://api.themoviedb.org/3/genre/movie/list?api_key=%s";
    private static final String MOVIE_TRAILER = "https://api.themoviedb.org/3/movie/%d/videos?api_key=%s";
    
    private final RestTemplate restTemplate;

    public MovieService(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    public List<Movie> getPopularMovies(int page){
        String url = String.format(POPULAR_MOVIES_URL,apiKey,page);
        TMDbMovieResponse response = restTemplate.getForObject(url,TMDbMovieResponse.class);
        return response.getResults();
    }

    public List<SearchResult> search(String query, int page) {
        String url = String.format(SEARCH_MEDIA_URL, apiKey, query, page);
        TMdBSearchResponse response = restTemplate.getForObject(url, TMdBSearchResponse.class);
        return response.getResults();
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

    public MediaVideo getMovieTrailer(Long id){
        String url = String.format(MOVIE_TRAILER,id,apiKey);
        TMDbVideosResponse response = restTemplate.getForObject(url, TMDbVideosResponse.class);
        if(response != null){
            return response.getResults().stream().filter(video -> 
            video.getType().equalsIgnoreCase("trailer") && video.getSite().equalsIgnoreCase("youtube"))
            .findFirst().orElse(null);
        }
        return null;
    }
}
