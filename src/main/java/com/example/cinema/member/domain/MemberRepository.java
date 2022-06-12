package com.example.cinema.member.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByMemberNumber(String memberNumber);
    Optional<Member> findByEmail(String email);
}
