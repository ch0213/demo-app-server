package com.example.cinema.movie.domain.movie;

import com.example.cinema.movie.infrastructure.MovieCustomRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MovieRepository extends JpaRepository<Movie, Long>, MovieCustomRepository {
    Optional<Movie> findByRunningTime(int runningTime);
}
