package com.example.cinema.screening.domain;

import com.example.cinema.screening.infrastructure.ScreeningCustomRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface ScreeningRepository extends JpaRepository<Screening, Long>, ScreeningCustomRepository {
    List<Screening> findAllByStartAtGreaterThanEqual(LocalDateTime time);
    List<Screening> findAllByStartAtEquals(LocalDateTime now);
}
