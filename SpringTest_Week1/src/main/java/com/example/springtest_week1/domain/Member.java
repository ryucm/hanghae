package com.example.springtest_week1.domain;

import com.example.springtest_week1.dto.MemberInfoResponseDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@NoArgsConstructor
@Entity
public class Member {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private long id;
    private String name;
    private String email;
    private String password;

    public Member(MemberInfoResponseDto memberInfoResponseDto) {
        this.id = memberInfoResponseDto.getId();
        this.name = memberInfoResponseDto.getName();
        this.email = memberInfoResponseDto.getEmail();
        this.password = memberInfoResponseDto.getPw();
    }
}

