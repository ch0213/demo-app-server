package com.example.cinema.auth.application.dto;

import com.example.cinema.member.domain.Gender;
import com.example.cinema.member.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class LoginResponse {

    private Long id;
    private String memberNumber;
    private String email;
    private Gender gender;
    private LocalDate birthDate;
    private JwtTokenDto tokens;

    public static LoginResponse of(Member member, JwtTokenDto tokens) {
        return new LoginResponse(
                member.getId(),
                member.getMemberNumber(),
                member.getEmail(),
                member.getGender(),
                member.getBirthDate(),
                tokens);
    }
}
