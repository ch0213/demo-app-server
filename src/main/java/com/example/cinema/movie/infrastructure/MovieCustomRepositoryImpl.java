package com.example.cinema.movie.infrastructure;

import com.example.cinema.movie.domain.movie.Movie;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.util.List;

import static com.example.cinema.movie.domain.movie.QMovie.movie;

@Slf4j
@RequiredArgsConstructor
public class MovieCustomRepositoryImpl implements MovieCustomRepository {

    private final JPAQueryFactory factory;

    @Override
    public Page<Movie> findByMovieTitleAndDate(Pageable pageable, String title, LocalDate date) {
        List<Movie> movies = factory.selectFrom(movie)
                .where(checkCondition(title, date))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(movie.start.asc(), movie.title.asc())
                .fetch();
        return PageableExecutionUtils.getPage(movies, pageable, movies::size);
    }

    private BooleanExpression checkCondition(String title, LocalDate date) {
        if (!StringUtils.hasText(title) && date != null) {
            return movie.start.loe(date).and(movie.end.goe(date));
        }

        if (StringUtils.hasText(title) && date == null) {
            return movie.title.contains(title).and(movie.end.goe(LocalDate.now()));
        }

        if (StringUtils.hasText(title) && date != null) {
            return movie.title.contains(title).and(movie.start.loe(date).and(movie.end.goe(date)));
        }

        return movie.end.goe(LocalDate.now());
    }
}
