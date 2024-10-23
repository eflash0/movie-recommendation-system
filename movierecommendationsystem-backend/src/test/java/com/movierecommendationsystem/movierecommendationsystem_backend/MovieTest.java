package com.movierecommendationsystem.movierecommendationsystem_backend;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import com.movierecommendationsystem.movierecommendationsystem_backend.dto.TMDbMovieResponse;
import com.movierecommendationsystem.movierecommendationsystem_backend.entity.Movie;
import com.movierecommendationsystem.movierecommendationsystem_backend.service.MovieService;

@SpringBootTest
public class MovieTest {
    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private MovieService movieService;

    @Value("${tmdb.api.key}")
    private String apiKey;

    private int page = 1;
    private TMDbMovieResponse tmDbMovieResponse;
    private Movie movie1;
    private Movie movie2;

    @BeforeEach
    void setUp(){
        movie1 = new Movie();
        movie1.setId(1L);
        movie1.setTitle("title 1");
        movie1.setOverview("overview 1");
        movie2 = new Movie();
        movie2.setId(2L);
        movie2.setTitle("title 2");
        movie2.setOverview("overview 2");
        tmDbMovieResponse = new TMDbMovieResponse();
        tmDbMovieResponse.setResults(Arrays.asList(movie1, movie2));
    }

    @Test
    void getMoviesTest(){
        when(restTemplate.getForObject(anyString(), eq(TMDbMovieResponse.class))).thenReturn(tmDbMovieResponse);
        List<Movie> movies = movieService.getPopularMovies(page);
        assertNotNull(movies);
        assertEquals(2, movies.size());
        assertEquals("title 1", movies.get(0).getTitle());
        assertEquals("title 2", movies.get(1).getTitle());
    }
}
