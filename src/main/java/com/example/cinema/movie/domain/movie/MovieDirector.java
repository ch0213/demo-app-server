package com.example.cinema.movie.domain.movie;

import com.example.cinema.movie.domain.director.Director;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;
import static lombok.AccessLevel.PROTECTED;

@Getter
@Entity
@NoArgsConstructor(access = PROTECTED)
public class MovieDirector {

    @Id @GeneratedValue
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "movie_id", nullable = false)
    private Movie movie;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "director_id", nullable = false)
    private Director director;

    public MovieDirector(Movie movie, Director director) {
        this.movie = movie;
        this.director = director;
    }
}
