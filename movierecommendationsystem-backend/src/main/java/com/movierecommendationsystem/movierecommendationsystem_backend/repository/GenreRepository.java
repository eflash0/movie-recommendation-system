package com.movierecommendationsystem.movierecommendationsystem_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.movierecommendationsystem.movierecommendationsystem_backend.entity.Genre;

@Repository
public interface GenreRepository extends JpaRepository<Genre,Long> {

}
