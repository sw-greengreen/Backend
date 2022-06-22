package com.hackathon.findtogether.dto.request;

import com.hackathon.findtogether.domain.Post;
import com.hackathon.findtogether.domain.ResolvingStatus;
import lombok.*;


import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class CreatePostDto {

    private String username;
    private String title;
    private String content;
    private String photo;
    private String hashtag;
    private ResolvingStatus resolvingStatus;
    private boolean isAnonymous;

    public static Post toEntity(CreatePostDto createPostDto) {
        Post post;
        post = Post.builder()
                .title(createPostDto.getTitle())
                .content(createPostDto.getContent())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .photo(createPostDto.getPhoto())
                .hashtag(createPostDto.getHashtag())
                .resolvingStatus(createPostDto.getResolvingStatus())
                .isAnonymous(createPostDto.isAnonymous())
                .build();
        return post;
    }
}
