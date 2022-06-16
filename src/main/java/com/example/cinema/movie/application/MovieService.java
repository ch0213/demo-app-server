package com.example.cinema.movie.application;

import com.example.cinema.movie.application.dto.MovieResponse;
import com.example.cinema.movie.domain.movie.MovieRepository;
import com.example.cinema.movie.presentation.dto.FindMovieRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MovieService {

    private final MovieRepository movieRepository;

    public Page<MovieResponse> findAllMovies(Pageable pageable, FindMovieRequest request) {
        return movieRepository.findByMovieTitleAndDate(pageable, request.getTitle(), request.getDate())
                .map(MovieResponse::from);
    }
}
