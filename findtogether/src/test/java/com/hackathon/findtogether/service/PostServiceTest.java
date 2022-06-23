package com.hackathon.findtogether.service;

import com.hackathon.findtogether.domain.Post;
import com.hackathon.findtogether.domain.ResolvingStatus;
import com.hackathon.findtogether.domain.User;
import com.hackathon.findtogether.domain.UserStatus;
import com.hackathon.findtogether.dto.request.CreatePostDto;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class PostServiceTest {

    @Autowired PostService postService;
    @Autowired UserService userService;

    @Test
    @Order(1)
    void createPost() {

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
//                .isAnonymous(true)
                .photo("url")
                .build();
        Post post = CreatePostDto.toEntity(createPostDto);

        //when
        Long id = postService.savePost(createPostDto);

        //then
        assertEquals(1,id);
    }
}