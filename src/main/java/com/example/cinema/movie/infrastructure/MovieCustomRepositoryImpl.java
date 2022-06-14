package com.example.cinema.movie.infrastructure;

import com.example.cinema.movie.domain.movie.Movie;
import com.example.cinema.movie.domain.movie.Operator;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;

import java.time.LocalDate;
import java.util.List;

import static com.example.cinema.movie.domain.movie.Operator.OR;
import static com.example.cinema.movie.domain.movie.QMovie.movie;

@RequiredArgsConstructor
public class MovieCustomRepositoryImpl implements MovieCustomRepository {

    private final JPAQueryFactory factory;

    @Override
    public Page<Movie> findByMovieTitleAndDate(Pageable pageable, String title, LocalDate date, Operator operator) {
        List<Movie> movies = factory.selectFrom(movie)
                .where(checkCondition(title, date, operator), movie.end.goe(LocalDate.now()))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
        return PageableExecutionUtils.getPage(movies, pageable, movies::size);
    }

    private BooleanExpression checkCondition(String title, LocalDate date, Operator operator) {
        if (title == null && date == null) {
            return bound(LocalDate.now());
        }

        if (title == null) {
            return bound(date);
        }

        if (date == null) {
            return movie.title.contains(title).and(bound(LocalDate.now()));
        }

        if (operator == OR) {
            return movie.title.contains(title).or(bound(date));
        }
        return movie.title.contains(title).and(bound(date));
    }

    private BooleanExpression bound(LocalDate date) {
        return movie.start.loe(date).and(movie.end.goe(date));
    }
}
