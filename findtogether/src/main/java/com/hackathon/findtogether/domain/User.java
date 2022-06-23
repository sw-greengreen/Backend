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

    private int point;

    @Enumerated(EnumType.STRING)
    private UserStatus achievement;

    //==비즈니스 로직==//
    public void addPoint(int point) {
        this.point += point;
    }

//    public static String phone(String src) {
//        if (src == null) {
//            return "";
//        }
//        if (src.length() == 8) {
//            return src.replaceFirst("^([0-9]{4})([0-9]{4})$", "$1-$2");
//        } else if (src.length() == 12) {
//            return src.replaceFirst("(^[0-9]{4})([0-9]{4})([0-9]{4})$", "$1-$2-$3");
//        }
//        return src.replaceFirst("(^02|[0-9]{3})([0-9]{3,4})([0-9]{4})$","$1-$2-$3");
//    }

}
