package com.example.cinema.movie.domain.movie;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.time.LocalDate;

import static javax.persistence.EnumType.STRING;
import static lombok.AccessLevel.PROTECTED;

@Getter
@Entity
@NoArgsConstructor(access = PROTECTED)
public class Movie {

    @Id @GeneratedValue
    private Long id;

    private String title;

    private LocalDate start;

    private LocalDate end;

    private int runningTime;

    private int reserveCount;

    private int audienceCount;

    @Enumerated(STRING)
    private MovieRating movieRating;

    @Lob
    private String description;

    @Builder
    public Movie(String title, LocalDate start, LocalDate end, int runningTime, MovieRating movieRating, String description) {
        this.title = title;
        this.start = start;
        this.end = end;
        this.runningTime = runningTime;
        this.movieRating = movieRating;
        this.description = description;
    }

    public Movie increaseAudienceCount(int audienceCount) {
        this.audienceCount += audienceCount;
        return this;
    }

    public Movie increaseReserveCount(int seatCount) {
        reserveCount += seatCount;
        return this;
    }

    public void decreaseSeatCount(int seatCount) {
        reserveCount -= seatCount;
    }

    public void decreaseAudienceCount(int seatCount) {
        audienceCount -= seatCount;
    }
}
