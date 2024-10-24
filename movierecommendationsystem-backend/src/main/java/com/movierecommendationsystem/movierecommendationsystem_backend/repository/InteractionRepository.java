package com.movierecommendationsystem.movierecommendationsystem_backend.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.movierecommendationsystem.movierecommendationsystem_backend.entity.Interaction;
import com.movierecommendationsystem.movierecommendationsystem_backend.entity.User;

@Repository
public interface InteractionRepository extends JpaRepository<Interaction,Long> {
    List<Interaction> findByMediaId(Long mediaId);
    List<Interaction> findByUser(User user);
    Optional<Interaction> findByUserAndMediaId(User user,Long mediaId);
    List<Interaction> findByUserAndIsWatchList(User user,boolean isWatchList);
    List<Interaction> findByUserAndIsFavorite(User user,boolean isFavorite);
}
