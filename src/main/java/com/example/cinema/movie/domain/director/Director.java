package com.example.cinema.movie.domain.director;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import static lombok.AccessLevel.PROTECTED;

@Getter
@Entity
@NoArgsConstructor(access = PROTECTED)
public class Director {

    @Id @GeneratedValue
    private Long id;

    private String name;

    public Director(String name) {
        this.name = name;
    }
}
