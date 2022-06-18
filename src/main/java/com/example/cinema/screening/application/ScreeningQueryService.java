package com.example.cinema.screening.application;

import com.example.cinema.screening.application.dto.ScreeningResponse;
import com.example.cinema.screening.domain.ScreeningRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ScreeningQueryService {

    private final ScreeningRepository scheduleRepository;

    public Page<ScreeningResponse> findMovieSchedule(Pageable pageable, Long movieId) {
        return scheduleRepository.findAllScreening(pageable, movieId)
                .map(ScreeningResponse::from);
    }
}
