package com.example.cinema.movie.domain.actor;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static lombok.AccessLevel.PROTECTED;

@Getter
@Entity
@NoArgsConstructor(access = PROTECTED)
public class Actor {

    @Id @GeneratedValue
    private Long id;

    private String name;

    public Actor(String name) {
        this.name = name;
    }
}
