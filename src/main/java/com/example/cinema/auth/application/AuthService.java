package com.example.cinema.auth.application;

import com.example.cinema.auth.application.dto.LoginResponse;
import com.example.cinema.auth.exception.NotExistMemberException;
import com.example.cinema.auth.exception.WrongPasswordException;
import com.example.cinema.auth.presentation.dto.LoginRequest;
import com.example.cinema.auth.util.JwtTokenProvider;
import com.example.cinema.member.domain.Member;
import com.example.cinema.member.domain.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    public LoginResponse login(LoginRequest loginRequest) {
        Member member = memberRepository.findByMemberNumber(loginRequest.getMemberNumber())
                .orElseThrow(NotExistMemberException::new);
        validatePassword(loginRequest, member);
        return LoginResponse.of(member, jwtTokenProvider.createTokens(member));
    }

    private void validatePassword(LoginRequest loginRequest, Member member) {
        if (!passwordEncoder.matches(loginRequest.getPassword(), member.getPassword())) {
            throw new WrongPasswordException();
        }
    }
}
