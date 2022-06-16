package com.example.cinema.movie.presentation.dto;

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
}
