package com.example.cinema.movie.domain.movie;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MovieRating {
    G_RATED("전체 관람가"),
    PG12("12세 이상 관람가"),
    PG15("15세 이상 관람가"),
    PG18("18세 이상 관람가");

    private final String description;
}
