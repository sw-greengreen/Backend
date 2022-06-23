package com.hackathon.findtogether.dto.request;

import com.hackathon.findtogether.domain.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SignupUserDto {
    private String name;
    private String username;
    private String password;
    private String phone;

    public SignupUserDto(User user) {
        this.name = user.getName();
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.phone = user.getPhone();
    }
}
