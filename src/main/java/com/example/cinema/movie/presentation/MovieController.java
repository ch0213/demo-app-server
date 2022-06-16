package com.example.cinema.movie.presentation;

import com.example.cinema.common.dto.CommonResponse;
import com.example.cinema.common.dto.PageInfo;
import com.example.cinema.movie.application.MovieService;
import com.example.cinema.movie.application.dto.MovieResponse;
import com.example.cinema.movie.presentation.dto.FindMovieRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.example.cinema.movie.presentation.MovieResponseMessage.FIND_MOVIE;

@RestController
@RequiredArgsConstructor
public class MovieController {

    private final MovieService movieService;

    @GetMapping("/movies")
    public CommonResponse<List<MovieResponse>> findAllMovies(FindMovieRequest request, Pageable pageable) {
        Page<MovieResponse> movies = movieService.findAllMovies(pageable, request);
        return CommonResponse.of(movies.getContent(), PageInfo.from(movies), FIND_MOVIE.getMessage());
    }
}
