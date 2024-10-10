package com.movierecommendationsystem.movierecommendationsystem_backend.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tvshows")
@NoArgsConstructor
@Getter
@Setter
public class TvShow {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long tvShowId;

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
