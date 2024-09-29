package com.movierecommendationsystem.movierecommendationsystem_backend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "interactions")
@NoArgsConstructor
@Getter
@Setter
public class Interaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long interactionId;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    private Long movieId;
    private double rating;
    private boolean isFavorite;
    private boolean isWatchLater;
}
