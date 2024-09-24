package com.movierecommendationsystem.movierecommendationsystem_backend.entity;

import java.util.List;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "movies")
@NoArgsConstructor
@Getter
@Setter
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long movieId;

    @Column(nullable = false)
    private String title;

    @Column(length = 1000)
    private String overview;

    private String releaseDate;

    private String posterPath;

    private Double popularity;

    private Double voteAverage;

    private Long tmdbId;

    @ManyToMany
    @JoinTable(name = "movie_genre",
        joinColumns = @JoinColumn(name = "movie_id"),
        inverseJoinColumns = @JoinColumn(name = "genre_id"),
        uniqueConstraints = @UniqueConstraint(columnNames = {"movie_id","genre_id"})
    )
    private List<Genre> genres;

}
