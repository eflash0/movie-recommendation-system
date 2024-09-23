package com.movierecommendationsystem.movierecommendationsystem_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.movierecommendationsystem.movierecommendationsystem_backend.entity.User;
import java.util.List;


@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    List<User> findByUsername(String username);
}
