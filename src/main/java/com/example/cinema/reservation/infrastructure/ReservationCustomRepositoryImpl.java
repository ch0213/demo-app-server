package com.example.cinema.reservation.infrastructure;

import com.example.cinema.member.domain.Member;
import com.example.cinema.member.domain.QMember;
import com.example.cinema.reservation.application.dto.*;
import com.example.cinema.reservation.domain.Reservation;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.jdbc.core.JdbcTemplate;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import static com.example.cinema.member.domain.QMember.member;
import static com.example.cinema.reservation.domain.QReservation.reservation;
import static com.example.cinema.screening.domain.QScreening.screening;

@RequiredArgsConstructor
public class ReservationCustomRepositoryImpl implements ReservationCustomRepository {

    private final JdbcTemplate jdbcTemplate;
    private final JPAQueryFactory factory;

    @Override
    public Page<Reservation> findAllReservation(Pageable pageable) {
        List<Reservation> reservations = factory.selectFrom(reservation)
                .innerJoin(reservation.screening, screening)
                .fetchJoin()
                .innerJoin(reservation.member, QMember.member)
                .fetchJoin()
                .where(reservation.screening.startAt.after(LocalDateTime.now()),
                        reservation.cancel.isFalse())
                .orderBy(reservation.screening.startAt.asc())
                .fetch();
        return PageableExecutionUtils.getPage(reservations, pageable, reservations::size);
    }

    @Override
    public Page<Reservation> findMyReservation(Pageable pageable, Member member, LocalDate start, LocalDate end) {
        List<Reservation> reservations = factory.selectFrom(reservation)
                .innerJoin(reservation.screening, screening)
                .fetchJoin()
                .innerJoin(reservation.member, QMember.member)
                .fetchJoin()
                .where(reservation.member.eq(member),
                        reservation.screening.startAt.after(LocalDateTime.now()),
                        reservation.cancel.isFalse(),
                        reservation.reserveAt.goe(start.atStartOfDay()).and(reservation.reserveAt.loe(end.atTime(LocalTime.MAX))))
                .orderBy(reservation.reserveAt.desc(), reservation.screening.startAt.desc())
                .fetch();
        return PageableExecutionUtils.getPage(reservations, pageable, reservations::size);
    }

    @Override
    public Page<Reservation> findAllCanceledReservation(Pageable pageable, Member member, LocalDate start, LocalDate end) {
        List<Reservation> reservations = factory.selectFrom(reservation)
                .innerJoin(reservation.screening, screening)
                .fetchJoin()
                .innerJoin(reservation.member, QMember.member)
                .fetchJoin()
                .where(reservation.member.eq(member),
                        reservation.cancel.isTrue(),
                        reservation.cancelAt.goe(start.atStartOfDay()).and(reservation.cancelAt.loe(end.atTime(LocalTime.MAX))))
                .orderBy(reservation.cancelAt.desc(), reservation.screening.startAt.desc())
                .fetch();
        return PageableExecutionUtils.getPage(reservations, pageable, reservations::size);
    }

    @Override
    public Page<Reservation> findAllViewHistory(Pageable pageable, Member member, LocalDate start, LocalDate end) {
        List<Reservation> reservations = factory.selectFrom(reservation)
                .innerJoin(reservation.screening, screening)
                .fetchJoin()
                .innerJoin(reservation.member, QMember.member)
                .fetchJoin()
                .where(reservation.member.eq(member),
                        reservation.screening.startAt.before(LocalDateTime.now()),
                        reservation.reserveAt.goe(start.atStartOfDay()).and(reservation.reserveAt.loe(end.atTime(LocalTime.MAX))),
                        reservation.cancel.isFalse())
                .orderBy(reservation.screening.startAt.desc())
                .fetch();
        return PageableExecutionUtils.getPage(reservations, pageable, reservations::size);
    }

    @Override
    public Page<ReservationRankResponse> findReservationRank(Pageable pageable) {
        String sql = "SELECT DENSE_RANK() OVER (ORDER BY RESERVE_COUNT DESC) AS RANK, *\n" +
                "FROM (SELECT M.NAME, M.MEMBER_NUMBER, COUNT(M.ID) AS RESERVE_COUNT\n" +
                "      from RESERVATION as R\n" +
                "               INNER JOIN MEMBER M on M.ID = R.MEMBER_ID\n" +
                "               INNER JOIN SCREENING S on S.ID = R.SCHEDULE_ID\n" +
                "      WHERE R.CANCEL IS FALSE\n" +
                "      GROUP BY M.ID\n" +
                "      ORDER BY COUNT(M.ID) DESC);";
        List<ReservationRankResponse> reservations = jdbcTemplate.query(sql, (rs, rowNum) -> new ReservationRankResponse(
                rs.getString("MEMBER_NUMBER"),
                rs.getString("NAME"),
                rs.getInt("RANK"),
                rs.getInt("RESERVE_COUNT")
        ));
        return PageableExecutionUtils.getPage(reservations, pageable, reservations::size);
    }

    public Page<ReservationCountResponse> findMembersReservationCount(Pageable pageable) {
        List<ReservationCountResponse> reservationCountResponses =
                factory.select(new QReservationCountResponse(
                                reservation.member.memberNumber,
                                reservation.member.name,
                                Expressions.asNumber(reservation.count().intValue())))
                        .from(reservation)
                        .innerJoin(reservation.member, member)
                        .where(reservation.cancel.isFalse())
                        .groupBy(reservation.member)
                        .orderBy(reservation.member.name.asc())
                        .fetch();

        return PageableExecutionUtils.getPage(reservationCountResponses, pageable, reservationCountResponses::size);
    }
}
