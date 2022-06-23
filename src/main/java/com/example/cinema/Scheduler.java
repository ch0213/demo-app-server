package com.example.cinema;

import com.example.cinema.screening.domain.Screening;
import com.example.cinema.screening.domain.ScreeningRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class Scheduler {

    private final ScreeningRepository screeningRepository;

    @Transactional
    @Scheduled(cron = "0 0/30 * * * ?")
    public void updateAudienceCount() {
        screeningRepository.findAllByStartAtEquals(LocalDateTime.now().withNano(0))
                .forEach(Screening::updateAudienceCount);
    }
}
