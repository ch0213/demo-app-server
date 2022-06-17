package com.example.cinema.screening.infrastructure;

import com.example.cinema.screening.domain.Screening;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ScreeningCustomRepository {
    Page<Screening> findAllScreening(Pageable pageable, Long movieId);
}
