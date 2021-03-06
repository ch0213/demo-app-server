package com.example.cinema.auth.application;

import com.example.cinema.auth.domain.MemberAdapter;
import com.example.cinema.member.domain.Member;
import com.example.cinema.member.domain.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;
    private static final String USER_NAME_NOT_FOUNT = "사용자를 찾을 수 없습니다.";

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member = memberRepository.findById(Long.valueOf(username))
                .orElseThrow(() -> new UsernameNotFoundException(USER_NAME_NOT_FOUNT));
        return new MemberAdapter(member);
    }
}
