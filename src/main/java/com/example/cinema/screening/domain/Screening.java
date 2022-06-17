package com.example.cinema.screening.domain;

import com.example.cinema.theater.domain.Theater;
import com.example.cinema.movie.domain.movie.Movie;
import com.example.cinema.screening.exception.AlreadyStartMovieException;
import com.example.cinema.screening.exception.NotEnoughSeatException;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.time.LocalDateTime;

import static javax.persistence.FetchType.LAZY;
import static lombok.AccessLevel.PROTECTED;

@Getter
@Entity
@NoArgsConstructor(access = PROTECTED)
public class Screening {

    @Id @GeneratedValue
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "cinema_id", nullable = false)
    private Theater theater;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "movie_id", nullable = false)
    private Movie movie;

    private int leftSeatCount;

    private LocalDateTime startAt;

    public Screening(Theater theater, Movie movie, LocalDateTime startAt) {
        this.theater = theater;
        this.movie = movie;
        this.leftSeatCount = theater.getSeatCount();
        this.startAt = startAt;
    }

    public Screening(Theater theater, Movie movie, int reservationCount, LocalDateTime startAt) {
        this.theater = theater;
        this.movie = movie.increaseReserveCount(reservationCount)
                .increaseAudienceCount(reservationCount);
        this.leftSeatCount = theater.getSeatCount() - reservationCount;
        this.startAt = startAt;
    }

    public Screening reserve(int seatCount) {
        validateReservation(seatCount);
        leftSeatCount -= seatCount;
        this.movie.increaseReserveCount(seatCount);
        return this;
    }

    private void validateReservation(int seatCount) {
        if (LocalDateTime.now().isAfter(startAt)) {
            throw new AlreadyStartMovieException();
        }

        if (leftSeatCount < seatCount) {
            throw new NotEnoughSeatException();
        }
    }

    public void updateAudienceCount() {
        this.movie.increaseAudienceCount(theater.getSeatCount() - leftSeatCount);
    }

    public void initAudienceCount() {
        this.movie.decreaseAudienceCount(theater.getSeatCount() - leftSeatCount);
    }

    public void cancel(int seatCount) {
        if (LocalDateTime.now().isAfter(startAt)) {
            throw new AlreadyStartMovieException();
        }
        leftSeatCount += seatCount;
        this.movie.decreaseSeatCount(seatCount);
    }

    public boolean isAfter(LocalDateTime date) {
        return this.startAt.isAfter(date);
    }
}
