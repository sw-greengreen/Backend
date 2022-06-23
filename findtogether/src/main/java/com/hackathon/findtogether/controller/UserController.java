package com.hackathon.findtogether.controller;

import com.hackathon.findtogether.domain.User;
import com.hackathon.findtogether.domain.UserStatus;
import com.hackathon.findtogether.dto.request.FindPasswordDto;
import com.hackathon.findtogether.dto.request.FindUsernameDto;
import com.hackathon.findtogether.dto.request.LoginUserDto;
import com.hackathon.findtogether.dto.request.SignupUserDto;
import com.hackathon.findtogether.service.UserService;
import com.hackathon.findtogether.util.SessionManager;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final SessionManager sessionManager;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/auth/signup")
    public Response createUser(@RequestBody @Valid SignupUserDto userDto) throws Exception {
        User user = User.builder()
                .name(userDto.getName())
                .username(userDto.getUsername())
                .password(userDto.getPassword())
                .phone(userDto.getPhone())
                .pointcount(0)
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
            return new Response(400, false, "로그인에 실패하였습니다. 해당 유저가 존재하지 않습니다.", requestUser);
        return new Response(200, true, "로그인에 성공하였습니다.", requestUser);
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/auth/loginSession")
    public Response loginUserWithSession(@RequestBody @Valid LoginUserDto userDto, HttpServletResponse response) {
        User requestUser = userService.login(userDto);

        if (requestUser == null)
            return new Response(400, false, "로그인에 실패하였습니다. 해당 유저가 존재하지 않습니다.", requestUser);

        sessionManager.createSession(requestUser, response);
        return new Response(200, true, "로그인에 성공하였습니다.", requestUser);
    }

    //아이디 찾기 -> 이름 전화번호 일치 -> 아이디 공개
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/auth/find/username")
    public Response findUsername(@RequestBody @Valid FindUsernameDto findUsernameDto) {
        User requestUser = userService.findCheckUsername(findUsernameDto);

        if (requestUser == null)
            return new Response(400, false, "아이디를 찾는데 실패하였습니다.", findUsernameDto);
        else {
            String findUsername = requestUser.getUsername();
            return new Response(200, true, "아이디를 찾는데 성공하였습니다.", findUsername);
        }
    }

    //비밀번호 찾기 -> 아이디 전화번호 일치 -> 비번 일부 공개
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/auth/find/password")
    public Response findUsername(@RequestBody @Valid FindPasswordDto findPasswordDto) {
        User requestUser = userService.findCheckPassword(findPasswordDto);

        if (requestUser == null)
            return new Response(400, false, "비밀번호를 찾는데 실패하였습니다.", findPasswordDto);
        else {
            String findPassword = requestUser.getPassword();
            int passwordLen = findPassword.length();
            String maskingPassword = findPassword.substring(0, passwordLen / 2);
            for (int i = 0; i < passwordLen - passwordLen / 2; i++)
                maskingPassword += "*";
            //String maskingPassword = findPassword.replaceAll("(?<=.{passwordLen}).", "x");
            return new Response(200, true, "비밀번호를 찾는데 성공하였습니다.", maskingPassword);
        }
    }

    //    //회원정보 조회 by username (마이페이지)
//    @ResponseStatus(HttpStatus.OK)
//    @GetMapping("/auth/{username}")
//    public Response getUserByUsername(@PathVariable String username) throws Exception {
//        User user = userService.findByUsername(username);
//        if (user == null)
//            return new Response(404,false,"not found user", "");
//        return new Response(200,true,"found user successfully", user);
//    }

    //회원정보 조회 by id (마이페이지)
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/auth/{userId}")
    public Response getUserByUsername(@PathVariable Long userId) throws Exception {
        User user = userService.findById(userId);
        if (user == null)
            return new Response(404,false,"not found user", "");
        return new Response(200,true,"found user successfully", user);
    }

    //사용자 포인트 수정 (댓글 채택 시 +10)
    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/auth/updatePoint/{userId}")
    public Response updatePointUser(@PathVariable Long userId) throws Exception {

        userService.upgradePointUser(userId);
        User user = userService.findById(userId);

        if (user.getPointcount() >= 5)
            userService.updateAchievementDetecter(userId);
        else if (user.getPointcount() > -5 && user.getPointcount() < 5)
            userService.updateAchievementBasic(userId);
        else if (user.getPointcount() <= -5)
            userService.updateAchievementLoser(userId);


        return new Response(200,true,"update user successfully", user);
    }
}
