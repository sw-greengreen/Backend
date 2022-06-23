package com.hackathon.findtogether.dto.response;

import com.hackathon.findtogether.domain.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LoginResultDto {

    private Long id;
    private String username;
    private String password;

    public LoginResultDto(User user) {
        id = user.getId();
        username = user.getUsername();
        password = user.getPassword();
    }
}
