package com.example.cinema.reservation.application;

import com.example.cinema.member.domain.Member;
import com.example.cinema.reservation.application.dto.ReservationCountResponse;
import com.example.cinema.reservation.application.dto.ReservationRankResponse;
import com.example.cinema.reservation.application.dto.ReservationResponse;
import com.example.cinema.reservation.domain.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ReservationQueryService {

    private final ReservationRepository reservationRepository;

    public Page<ReservationResponse> myReservations(Pageable pageable, Member member, LocalDate start, LocalDate end) {
        return reservationRepository.findMyReservation(pageable, member, start, end)
                .map(ReservationResponse::from);
    }

    public Page<ReservationResponse> allCanceledReservation(Pageable pageable, Member member, LocalDate start, LocalDate end) {
        return reservationRepository.findAllCanceledReservation(pageable, member, start, end)
                .map(ReservationResponse::from);
    }

    public Page<ReservationResponse> allViewHistory(Pageable pageable, Member member, LocalDate start, LocalDate end) {
        return reservationRepository.findAllViewHistory(pageable, member, start, end)
                .map(ReservationResponse::from);
    }

    public Page<ReservationResponse> allReservations(Pageable pageable) {
        return reservationRepository.findAllReservation(pageable)
                .map(ReservationResponse::from);
    }

    public Page<ReservationCountResponse> allMemberReservationsCount(Pageable pageable) {
        return reservationRepository.findMembersReservationCount(pageable);
    }

    public Page<ReservationRankResponse> allMemberReservationRank(Pageable pageable) {
        return reservationRepository.findReservationRank(pageable);
    }
}
