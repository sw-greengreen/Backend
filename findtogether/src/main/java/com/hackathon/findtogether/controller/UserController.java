package com.hackathon.findtogether.controller;

import com.hackathon.findtogether.domain.User;
import com.hackathon.findtogether.domain.UserStatus;
import com.hackathon.findtogether.dto.request.LoginUserDto;
import com.hackathon.findtogether.dto.request.SignupUserDto;
import com.hackathon.findtogether.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/auth/signup")
    public Response createUser(@RequestBody @Valid SignupUserDto userDto) throws Exception {
        User user = User.builder()
                .name(userDto.getName())
                .username(userDto.getUsername())
                .password(userDto.getPassword())
                .point(0)
                .achievement(UserStatus.BASIC)
                .build();
        userService.join(user);
        return new Response(201, true, "회원가입에 성공하였습니다.", userDto);
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/auth/login")
    public Response loginUser(@RequestBody @Valid LoginUserDto userDto) {
        User requestUser = userService.login(userDto);

        if (requestUser == null)
            return new Response(400, false, "로그인에 실패하였습니다. 해당 유저가 존재하지 않습니다.", userDto);
        return new Response(200, true, "로그인에 성공하였습니다.", userDto);
    }
}