package com.hackathon.findtogether.service;

import com.hackathon.findtogether.domain.User;
import com.hackathon.findtogether.domain.UserStatus;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class AlarmServiceTest {

    @Autowired AlarmService alarmService;
    @Autowired UserService userService;

    @Test
    void updateAlarm() {
        //given
        User user = User.builder()
                .name("김송이")
                .username("보핍")
                .password("1234")
                .point(30)
                .achievement(UserStatus.BASIC)
                .build();

        userService.join(user);


        //when

        //then
    }
}