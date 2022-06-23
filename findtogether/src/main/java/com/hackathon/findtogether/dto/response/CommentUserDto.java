package com.hackathon.findtogether.dto.response;

import com.hackathon.findtogether.domain.Comment;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommentUserDto {

    private String username;
    private Long id;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String isAnonymous;

    public CommentUserDto(Comment comment) {
        username = comment.getWriter().getUsername(); //Lazy 초기화
        id = comment.getId();
        content = comment.getContent();
        createdAt = comment.getCreatedAt();
        updatedAt = comment.getUpdatedAt();
        isAnonymous = comment.getIsAnonymous();
    }
}
