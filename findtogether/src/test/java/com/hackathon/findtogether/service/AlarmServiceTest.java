package com.hackathon.findtogether.service;

import com.hackathon.findtogether.domain.Comment;
import com.hackathon.findtogether.domain.Post;
import com.hackathon.findtogether.domain.User;
import com.hackathon.findtogether.domain.UserStatus;
import com.hackathon.findtogether.dto.request.CreatePostDto;
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

        CreatePostDto createPostDto = CreatePostDto.builder()
                .username("보핍")
                .title("물건 찾아용")
                .content("찾아주세요..")
                .hashtag("#지갑")
                .isAnonymous("true")
                .photo("url")
                .build();
        Post post = CreatePostDto.toEntity(createPostDto);

        Comment comment = Comment.builder()
                .post(post)
                .writer(user)
                .content("댓글 테스트")
                .build();


        //when

        //then
    }
}