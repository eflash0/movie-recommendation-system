package com.movierecommendationsystem.movierecommendationsystem_backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.movierecommendationsystem.movierecommendationsystem_backend.entity.Media;
import com.movierecommendationsystem.movierecommendationsystem_backend.service.RecommendationService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/recommendation")
@Slf4j
public class RecommendationController {
    @Autowired
    private RecommendationService recommendationService;

    @PostMapping("/train")
    public ResponseEntity<Void> trainModel() {
        recommendationService.trainRecommendationModel();
        return ResponseEntity.ok().build();
    }

    // Endpoint to get recommendations for a user
    @PostMapping("/get-recommendations")
    public ResponseEntity<List<Media>> getRecommendations(@RequestParam Long userId) {
        log.info("--------------------userId : "+userId);
        List<Media> recommendations = recommendationService.getRecommendations(userId);
        return ResponseEntity.ok(recommendations);
    }
}
