package com.movierecommendationsystem.movierecommendationsystem_backend.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.movierecommendationsystem.movierecommendationsystem_backend.dto.SeasonDto;
import com.movierecommendationsystem.movierecommendationsystem_backend.entity.MediaVideo;
import com.movierecommendationsystem.movierecommendationsystem_backend.entity.TvShow;
import com.movierecommendationsystem.movierecommendationsystem_backend.service.TvShowService;



@RestController
@RequestMapping("/tv-shows")
public class TvShowController {
    @Autowired
    private TvShowService tvShowService;

    @GetMapping
    public ResponseEntity<List<TvShow>> getPopularTvShows(@RequestParam(defaultValue = "1") int page){
        List<TvShow> TvShows = tvShowService.getPopularTvShows(page);
        return ResponseEntity.ok(TvShows);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TvShow> getTvShowDetails(@PathVariable Long id){
        TvShow TvShow = tvShowService.getTvShowDetails(id);
        return ResponseEntity.ok(TvShow);
    }

    @GetMapping("/{id}/trailer")
    public ResponseEntity<MediaVideo> getTrailers(@PathVariable Long id){
        MediaVideo trailer = tvShowService.getTvShowTrailer(id);
        return ResponseEntity.ok(trailer);
    }

    @GetMapping("/{tvShowId}/seasons/{seasonNumber}")
    public ResponseEntity<SeasonDto> getSeasonDetail(@PathVariable Long tvShowId,
    @PathVariable int seasonNumber){
        SeasonDto season = tvShowService.getTvShowSeasonDetails(tvShowId, seasonNumber);
        return ResponseEntity.ok(season);
    }
}
