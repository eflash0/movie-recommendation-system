package com.movierecommendationsystem.movierecommendationsystem_backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.movierecommendationsystem.movierecommendationsystem_backend.repository.InteractionRepository;

@Service
public class RecommendationService {
    @Autowired
    private InteractionRepository interactionRepository;
    private final String pythonUrl = "http://localhost:5000";
}
