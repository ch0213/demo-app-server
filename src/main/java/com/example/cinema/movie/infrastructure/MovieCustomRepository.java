package com.example.cinema.movie.infrastructure;

import com.example.cinema.movie.domain.movie.Movie;
import com.example.cinema.movie.domain.movie.Operator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;

public interface MovieCustomRepository {
    Page<Movie> findByMovieTitleAndDate(Pageable pageable, String title, LocalDate date, Operator operator);
}
