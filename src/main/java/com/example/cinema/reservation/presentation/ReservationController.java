package com.example.cinema.reservation.presentation;

import com.example.cinema.auth.util.LoginUser;
import com.example.cinema.common.dto.CommonResponse;
import com.example.cinema.common.dto.PageInfo;
import com.example.cinema.member.domain.Member;
import com.example.cinema.reservation.application.ReservationQueryService;
import com.example.cinema.reservation.application.ReservationService;
import com.example.cinema.reservation.application.dto.ReservationCountResponse;
import com.example.cinema.reservation.application.dto.ReservationRankResponse;
import com.example.cinema.reservation.application.dto.ReservationResponse;
import com.example.cinema.reservation.infrastructure.MailService;
import com.example.cinema.reservation.presentation.dto.ReservationRequest;
import com.example.cinema.reservation.presentation.dto.SearchReservationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static com.example.cinema.reservation.presentation.ReservationResponseMessage.*;

@RestController
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationQueryService reservationQueryService;
    private final ReservationService reservationService;

    @PostMapping("/screenings/{screeningId}/reservations")
    public CommonResponse<Void> reserve(@LoginUser Member member, @PathVariable Long screeningId, @Valid @RequestBody ReservationRequest request) {
        reservationService.reserve(member, screeningId, request.getSeatCount());
        return CommonResponse.from(RESERVATION_SUCCESS.getMessage());
    }

    @DeleteMapping("/reservations/{reservationId}")
    public CommonResponse<Void> cancel(@LoginUser Member member, @PathVariable Long reservationId) {
        reservationService.cancel(member, reservationId);
        return CommonResponse.from(CANCEL_SUCCESS.getMessage());
    }

    @GetMapping("/reservations/me")
    public CommonResponse<List<ReservationResponse>> myReservations(
            @LoginUser Member member, Pageable pageable, @Valid SearchReservationRequest request) {
        Page<ReservationResponse> reservations = reservationQueryService.myReservations(
                pageable, member, request.getStart(), request.getEnd());
        return CommonResponse.of(reservations.getContent(), PageInfo.from(reservations), FIND_RESERVATION.getMessage());
    }

    @GetMapping("/reservations/canceled")
    public CommonResponse<List<ReservationResponse>> allCanceledReservation(
            @LoginUser Member member, Pageable pageable, @Valid SearchReservationRequest request) {
        Page<ReservationResponse> reservations = reservationQueryService.allCanceledReservation(
                pageable, member, request.getStart(), request.getEnd());
        return CommonResponse.of(reservations.getContent(), PageInfo.from(reservations), FIND_CANCELED_RESERVATION.getMessage());
    }

    @GetMapping("/reservations/view")
    public CommonResponse<List<ReservationResponse>> allViewHistory(
            @LoginUser Member member, Pageable pageable, @Valid SearchReservationRequest request) {
        Page<ReservationResponse> reservations = reservationQueryService.allViewHistory(
                pageable, member, request.getStart(), request.getEnd());
        return CommonResponse.of(reservations.getContent(), PageInfo.from(reservations), FIND_VIEW_HISTORY.getMessage());
    }

    @GetMapping("/reservations")
    public CommonResponse<List<ReservationResponse>> allReservations(Pageable pageable) {
        Page<ReservationResponse> reservations = reservationQueryService.allReservations(pageable);
        return CommonResponse.of(reservations.getContent(), PageInfo.from(reservations), FIND_MEMBERS_RESERVATION.getMessage());
    }

    @GetMapping("/reservations/count")
    public CommonResponse<List<ReservationCountResponse>> membersReservationsCount(Pageable pageable) {
        Page<ReservationCountResponse> reservations = reservationQueryService.allMemberReservationsCount(pageable);
        return CommonResponse.of(reservations.getContent(), PageInfo.from(reservations), FIND_MEMBERS_RESERVATION_COUNT.getMessage());
    }

    @GetMapping("/reservations/rank")
    public CommonResponse<List<ReservationRankResponse>> membersReservationsRank(Pageable pageable) {
        Page<ReservationRankResponse> reservations = reservationQueryService.allMemberReservationRank(pageable);
        return CommonResponse.of(reservations.getContent(), PageInfo.from(reservations), FIND_MEMBERS_RESERVATION_RANK.getMessage());
    }

    private final MailService mailService;
    @PostMapping("/mails")
    public CommonResponse<Void> test() {
        mailService.sendMail(177L);
        return CommonResponse.from("dd");
    }
}
