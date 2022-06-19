package com.example.cinema.reservation.domain;

import com.example.cinema.member.domain.Member;
import com.example.cinema.reservation.exception.AlreadyCancelException;
import com.example.cinema.reservation.exception.NotEnoughAuthorityException;
import com.example.cinema.screening.domain.Screening;
import com.example.cinema.reservation.exception.WrongSeatCountException;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.time.LocalDateTime;

import static javax.persistence.FetchType.LAZY;
import static lombok.AccessLevel.PROTECTED;

@Getter
@Entity
@NoArgsConstructor(access = PROTECTED)
public class Reservation {

    private static final int MAX_SEAT_COUNT = 10;

    @Id @GeneratedValue
    private Long id;

    private int seatCount;

    private boolean cancel;

    private LocalDateTime reserveAt;

    private LocalDateTime cancelAt;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "schedule_id", nullable = false)
    private Screening screening;

    public Reservation(Member member, Screening screening, int seatCount) {
        validate(seatCount);
        this.member = member;
        this.screening = screening.reserve(seatCount);
        this.seatCount = seatCount;
        this.reserveAt = LocalDateTime.now();
        this.cancel = false;
    }

    public Reservation(int seatCount, boolean cancel, Member member, Screening screening) {
        this.seatCount = seatCount;
        this.cancel = cancel;
        this.member = member;
        this.screening = screening;

        if (screening.getStartAt().isAfter(LocalDateTime.now())) {
            this.reserveAt = LocalDateTime.now().minusDays(1);
        } else {
            this.reserveAt = screening.getStartAt().minusHours(1);
        }

        if (cancel) {
            cancelAt = generateCancelTime(screening);
        }
    }

    private LocalDateTime generateCancelTime(Screening screening) {
        if (screening.isAfter(LocalDateTime.now())) {
            return LocalDateTime.now().minusDays(1);
        }
        return screening.getStartAt().minusHours(5);
    }

    private void validate(int seatCount) {
        if (seatCount > MAX_SEAT_COUNT) {
            throw new WrongSeatCountException();
        }
    }

    public void cancel(Member member) {
        validateCancel(member);
        screening.cancel(seatCount);

        cancel = true;
        cancelAt = LocalDateTime.now();
    }

    private void validateCancel(Member member) {
        if (cancel) {
            throw new AlreadyCancelException();
        }

        if (!this.member.equals(member)) {
            throw new NotEnoughAuthorityException();
        }
    }
}
