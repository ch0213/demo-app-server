package com.example.cinema.reservation.application;

import com.example.cinema.member.domain.Member;
import com.example.cinema.reservation.exception.NonExistentReservationException;
import com.example.cinema.reservation.infrastructure.MailService;
import com.example.cinema.screening.domain.Screening;
import com.example.cinema.screening.domain.ScreeningRepository;
import com.example.cinema.screening.exception.NonExistentScreeningException;
import com.example.cinema.reservation.domain.Reservation;
import com.example.cinema.reservation.domain.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final ScreeningRepository scheduleRepository;
    private final MailService mailService;

    public void reserve(Member member, Long scheduleId, int seatCount) {
        Screening schedule = scheduleRepository.findById(scheduleId)
                .orElseThrow(NonExistentScreeningException::new);
        Reservation reservation = reservationRepository.save(new Reservation(member, schedule, seatCount));
        mailService.sendMail(reservation.getId());
    }

    public void cancel(Member member, Long reservationId) {
        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(NonExistentReservationException::new);
        reservation.cancel(member);
    }
}
