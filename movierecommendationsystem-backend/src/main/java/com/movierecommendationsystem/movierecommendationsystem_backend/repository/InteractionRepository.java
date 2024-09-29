package com.movierecommendationsystem.movierecommendationsystem_backend.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.movierecommendationsystem.movierecommendationsystem_backend.entity.Interaction;

@Repository
public interface InteractionRepository extends JpaRepository<Interaction,Long> {
    List<Interaction> findByMovieId(Long movieId);
    List<Interaction> findByUser(Long userId);
    Optional<Interaction> findByUserAndMovieId(Long userId,Long movieId);
    List<Interaction> findByUserIdAndIsWatchLaterTrue(Long userId);
    List<Interaction> findByUserIdAndIsFavorite(Long userId);
}
