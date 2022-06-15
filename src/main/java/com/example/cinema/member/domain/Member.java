package com.example.cinema.member.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.time.LocalDate;
import java.util.Objects;

import static javax.persistence.EnumType.STRING;
import static lombok.AccessLevel.PROTECTED;

@Getter
@Entity
@NoArgsConstructor(access = PROTECTED)
public class Member {

    @Id @GeneratedValue
    private Long id;

    private String memberNumber;

    private String password;

    private String name;

    private String email;

    private Gender gender;

    private LocalDate birthDate;

    @Enumerated(STRING)
    private Role role;

    @Builder
    public Member(String password, String name, String email, Gender gender, LocalDate birthDate, Role role) {
        this.password = password;
        this.name = name;
        this.email = email;
        this.gender = gender;
        this.birthDate = birthDate;
        this.role = role;
    }

    public Member generateMemberNumber() {
        memberNumber = LocalDate.now() + String.valueOf(id);
        return this;
    }

    public String securityRoleType() {
        return role.getRole();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Member member = (Member) o;
        return Objects.equals(getId(), member.getId()) && Objects.equals(getMemberNumber(), member.getMemberNumber()) && Objects.equals(getPassword(), member.getPassword()) && Objects.equals(getName(), member.getName()) && Objects.equals(getEmail(), member.getEmail()) && getGender() == member.getGender() && Objects.equals(getBirthDate(), member.getBirthDate()) && getRole() == member.getRole();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getMemberNumber(), getPassword(), getName(), getEmail(), getGender(), getBirthDate(), getRole());
    }
}
