package com.hackathon.findtogether.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hackathon.findtogether.dto.request.CreatePostDto;
import com.hackathon.findtogether.dto.request.UpdatePostDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class Post {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user; // 연관관계의 주인

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    private String photo;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private String hashtag;

    @Enumerated(EnumType.STRING)
    private ResolvingStatus resolvingStatus;

    private String isAnonymous;

    //==게시물 생성 메서드==//
    public static Post createPost(CreatePostDto createPostDto, User user){
        Post post;
        post = Post.builder()
                .user(user)
                .title(createPostDto.getTitle())
                .content(createPostDto.getContent())
                .photo(createPostDto.getPhoto())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .hashtag(createPostDto.getHashtag())
                .isAnonymous(createPostDto.getIsAnonymous())
                .build();

        return post;
    }

    //==게시물 수정 메서드==//
    public void updatePost(UpdatePostDto updatePostDto){
        this.title = updatePostDto.getTitle();
        this.content = updatePostDto.getContent();
        this.photo = updatePostDto.getPhoto();
        this.hashtag = updatePostDto.getHashtag();
        this.updatedAt = LocalDateTime.now();
        this.isAnonymous = updatePostDto.getIsAnonymous();
    }
}
