package com.movierecommendationsystem.movierecommendationsystem_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

import com.movierecommendationsystem.movierecommendationsystem_backend.entity.Movie;
import com.movierecommendationsystem.movierecommendationsystem_backend.entity.Genre;


@Repository
public interface MovieRepository extends JpaRepository<Movie,Long> {
    List<Movie> findByGenres(List<Genre> genres);
}
