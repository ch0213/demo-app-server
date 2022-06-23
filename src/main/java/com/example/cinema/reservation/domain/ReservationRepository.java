package com.example.cinema.reservation.domain;

import com.example.cinema.reservation.infrastructure.ReservationCustomRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, Long>, ReservationCustomRepository {
}
