package com.example.cinema.screening.infrastructure;

import com.example.cinema.screening.domain.Screening;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;

import java.time.LocalDateTime;
import java.util.List;

import static com.example.cinema.theater.domain.QTheater.theater;
import static com.example.cinema.movie.domain.movie.QMovie.movie;
import static com.example.cinema.screening.domain.QScreening.screening;

@RequiredArgsConstructor
public class ScreeningCustomRepositoryImpl implements ScreeningCustomRepository {

    private final JPAQueryFactory factory;

    @Override
    public Page<Screening> findAllScreening(Pageable pageable, Long movieId) {
        List<Screening> screenings = factory.selectFrom(screening)
                .innerJoin(screening.movie, movie)
                .fetchJoin()
                .innerJoin(screening.theater, theater)
                .fetchJoin()
                .where(screening.movie.id.eq(movieId), screening.startAt.after(LocalDateTime.now()))
                .orderBy(screening.startAt.asc())
                .fetch();
        return PageableExecutionUtils.getPage(screenings, pageable, screenings::size);
    }
}
