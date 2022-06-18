package com.example.cinema.screening.presentation;

import com.example.cinema.common.dto.CommonResponse;
import com.example.cinema.common.dto.PageInfo;
import com.example.cinema.screening.application.ScreeningQueryService;
import com.example.cinema.screening.application.dto.ScreeningResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.example.cinema.screening.presentation.ScreeningResponseMessage.FIND_SCHEDULE;

@RestController
@RequiredArgsConstructor
public class ScreeningController {

    private final ScreeningQueryService screeningQueryService;

    @GetMapping("/screenings")
    public CommonResponse<List<ScreeningResponse>> findMovieScreening(@RequestParam Long movieId, Pageable pageable) {
        Page<ScreeningResponse> schedule = screeningQueryService.findMovieSchedule(pageable, movieId);
        return CommonResponse.of(schedule.getContent(), PageInfo.from(schedule), FIND_SCHEDULE.getMessage());
    }
}
