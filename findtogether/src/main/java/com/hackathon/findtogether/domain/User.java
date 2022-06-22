package com.hackathon.findtogether.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class User {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(length = 20)
    private String name;

    @NotEmpty(message = "아이디를 입력해주세요")
    @Column(length = 20, nullable = false, unique = true)
    private String username;

    @Column(length = 50)
    private String password;

    private int point;

    @Enumerated(EnumType.STRING)
    private UserStatus achievement;
}
