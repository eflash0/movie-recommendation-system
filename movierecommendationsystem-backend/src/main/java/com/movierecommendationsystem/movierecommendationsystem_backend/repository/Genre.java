package com.movierecommendationsystem.movierecommendationsystem_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Genre extends JpaRepository<Genre,Long> {

}
