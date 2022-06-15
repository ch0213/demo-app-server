package com.example.cinema.member.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Gender {
    MAN("남성"),
    WOMAN("여성");

    private final String description;
}
