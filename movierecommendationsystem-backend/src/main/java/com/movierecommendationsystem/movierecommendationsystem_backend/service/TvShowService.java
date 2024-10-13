package com.movierecommendationsystem.movierecommendationsystem_backend.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.movierecommendationsystem.movierecommendationsystem_backend.dto.GenreResponse;
import com.movierecommendationsystem.movierecommendationsystem_backend.dto.SeasonDto;
import com.movierecommendationsystem.movierecommendationsystem_backend.dto.TMDbMovieResponse;
import com.movierecommendationsystem.movierecommendationsystem_backend.dto.TMDbTvShowResponse;
import com.movierecommendationsystem.movierecommendationsystem_backend.dto.TMDbVideosResponse;
import com.movierecommendationsystem.movierecommendationsystem_backend.dto.TvShowDto;
import com.movierecommendationsystem.movierecommendationsystem_backend.entity.Genre;
import com.movierecommendationsystem.movierecommendationsystem_backend.entity.MediaVideo;

@Service
public class TvShowService {
    @Value("${tmdb.api.key}")
    private String apiKey;
    private static final String POPULAR_TV_SHOWS_URL = "https://api.themoviedb.org/3/tv/popular?api_key=%s&page=%d";
    private static final String TV_SHOW_DETAILS_URL = "https://api.themoviedb.org/3/tv/%d?api_key=%s";
    private static final String TV_SHOW_GENRES_URL = "https://api.themoviedb.org/3/genre/tv/list?api_key=%s";
    private static final String TV_SHOW_TRAILER_URL = "https://api.themoviedb.org/3/tv/%d/videos?api_key=%s";
    private static final String TV_SHOW_SEASON_URL = "https://api.themoviedb.org/3/tv/%d/season/%d?api_key=%s";

    private final RestTemplate restTemplate;

    public TvShowService(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    public List<TvShowDto> getPopularTvShows(int page) {
        String url = String.format(POPULAR_TV_SHOWS_URL, apiKey, page);
        TMDbTvShowResponse response = restTemplate.getForObject(url, TMDbTvShowResponse.class);
        return response.getResults();
    }

    public TvShowDto getTvShowDetails(Long tvShowId) {
        String url = String.format(TV_SHOW_DETAILS_URL, tvShowId, apiKey);
        return restTemplate.getForObject(url, TvShowDto.class);
    }

    public List<Genre> getTvShowGenres() {
        String url = String.format(TV_SHOW_GENRES_URL, apiKey);
        GenreResponse response = restTemplate.getForObject(url, GenreResponse.class);
        return response.getGenres();
    }

    public MediaVideo getTvShowTrailer(Long id) {
        String url = String.format(TV_SHOW_TRAILER_URL, id, apiKey);
        TMDbVideosResponse response = restTemplate.getForObject(url, TMDbVideosResponse.class);
        if (response != null) {
            return response.getResults().stream()
                    .filter(video -> video.getType().equalsIgnoreCase("trailer") && video.getSite().equalsIgnoreCase("youtube"))
                    .findFirst()
                    .orElse(null);
        }
        return null;
    }


    public SeasonDto getTvShowSeasonDetails(Long tvShowId, int seasonNumber){
        String url = String.format(TV_SHOW_SEASON_URL, tvShowId, seasonNumber, apiKey);
        return restTemplate.getForObject(url, SeasonDto.class);
    }
}
