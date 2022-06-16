package com.example.cinema.movie.application.dto;

import com.example.cinema.movie.domain.movie.Movie;
import com.example.cinema.movie.domain.movie.MovieRating;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.persistence.Enumerated;
import java.time.LocalDate;

import static javax.persistence.EnumType.STRING;

@Getter
@AllArgsConstructor
public class MovieResponse {

    private Long id;

    private String title;

    private LocalDate start;

    private LocalDate end;

    private int runningTime;

    @Enumerated(STRING)
    private MovieRating movieRating;

    private int reserveCount;

    private int audienceCount;

    private String description;

    private Boolean isStart;

    public static MovieResponse from(Movie movie) {
        boolean isStart = true;
        if (movie.getStart().isAfter(LocalDate.now())) {
            isStart = false;
        }
        return new MovieResponse(
                movie.getId(),
                movie.getTitle(),
                movie.getStart(),
                movie.getEnd(),
                movie.getRunningTime(),
                movie.getMovieRating(),
                movie.getReserveCount(),
                movie.getAudienceCount(),
                movie.getDescription(),
                isStart
        );
    }
}
