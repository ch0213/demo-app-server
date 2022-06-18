package com.example.cinema.theater.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import static lombok.AccessLevel.PROTECTED;

@Getter
@Entity
@NoArgsConstructor(access = PROTECTED)
public class Theater {

    @Id @GeneratedValue
    private Long id;

    private String name;

    private int seatCount;

    public Theater(String name, int seatCount) {
        this.name = name;
        this.seatCount = seatCount;
    }
}
