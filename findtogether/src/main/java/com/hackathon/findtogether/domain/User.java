package com.hackathon.findtogether.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class User {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @NotEmpty(message = "이름을 입력해주세요")
    @Column(length = 20)
    private String name;

    @NotEmpty(message = "아이디를 입력해주세요")
    @Column(length = 20, nullable = false, unique = true)
    private String username;

    @NotEmpty(message = "비밀번호를 입력해주세요")
    @Column(length = 50, nullable = false)
    private String password;

    @NotEmpty(message = "전화번호를 입력해주세요")
    @Column(length = 20)
    private String phone;

//    private int point;
//    private int detectcount;

    private int pointcount;

    @Enumerated(EnumType.STRING)
    private UserStatus achievement;

    //==비즈니스 로직==//
    public void addPoint() {
        this.pointcount += 1;
    }

    public void substractPoint() {
        this.pointcount -= 1;
    }
}
