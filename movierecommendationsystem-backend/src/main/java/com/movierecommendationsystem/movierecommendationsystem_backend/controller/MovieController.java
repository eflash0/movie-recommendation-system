package com.movierecommendationsystem.movierecommendationsystem_backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.movierecommendationsystem.movierecommendationsystem_backend.dto.SearchResult;
import com.movierecommendationsystem.movierecommendationsystem_backend.entity.MediaVideo;
import com.movierecommendationsystem.movierecommendationsystem_backend.entity.Movie;
import com.movierecommendationsystem.movierecommendationsystem_backend.service.MovieService;

import java.util.List;

@RestController
@RequestMapping(path = "/movies")
public class MovieController {
    @Autowired
    private MovieService movieService;

    @GetMapping
    public ResponseEntity<List<Movie>> getPopularMovies(@RequestParam(defaultValue = "1") int page){
        List<Movie> movies = movieService.getPopularMovies(page);
        return ResponseEntity.ok(movies);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Movie> getMovieDetails(@PathVariable Long id){
        Movie movie = movieService.getMovieDetails(id);
        return ResponseEntity.ok(movie);
    }

    @GetMapping("/{id}/trailer")
    public ResponseEntity<MediaVideo> getTrailers(@PathVariable Long id){
        MediaVideo trailer = movieService.getMovieTrailer(id);
        return ResponseEntity.ok(trailer);
    }

    @GetMapping("search")
    public ResponseEntity<List<SearchResult>> searchMovies(@RequestParam String query,@RequestParam(defaultValue = "1") int page){
        List<SearchResult> results = movieService.search(query, page);
        return ResponseEntity.ok(results);
    }

}
