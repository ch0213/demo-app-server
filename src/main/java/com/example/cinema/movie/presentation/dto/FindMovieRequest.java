package com.example.cinema.movie.presentation.dto;

import com.example.cinema.movie.domain.movie.Operator;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class FindMovieRequest {

    private String title;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;

    private Operator operator;
}
