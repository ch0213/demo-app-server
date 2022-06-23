package com.example.cinema.reservation.infrastructure;

import com.example.cinema.reservation.domain.Reservation;
import com.example.cinema.reservation.domain.ReservationRepository;
import com.example.cinema.reservation.exception.NonExistentReservationException;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class MailService {

    private static final String MAIL_FORM = "- 예매자: %s\n- 영화 제목: %s\n- 상영관: %s\n- 예매 수: %d\n- 시간 : %s";
    private static final String MAIL_SUBJECT_FORM = "[CNU CINEMA] 예매 완료";

    private final ReservationRepository reservationRepository;
    private final JavaMailSender javaMailSender;

    @Transactional(readOnly = true)
    public void sendMail(Long reservationId) {
        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(NonExistentReservationException::new);

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(reservation.getMember().getEmail());
        mailMessage.setSubject(MAIL_SUBJECT_FORM);
        mailMessage.setText(String.format(
                MAIL_FORM,
                reservation.getMember().getName(),
                reservation.getScreening().getMovie().getTitle(),
                reservation.getScreening().getTheater().getName(),
                reservation.getSeatCount(),
                reservation.getScreening().getStartAt().toString().replaceAll("T", " ")
        ));

        javaMailSender.send(mailMessage);
    }
}
