package com.hackathon.findtogether.dto.request;

import com.hackathon.findtogether.domain.Comment;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class CreateCommentDto {

    private Long postId; //게시글 (제목, 작성자)
    private String writer; //댓글 작성자
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String isAnonymous;

    public static Comment toEntity(CreateCommentDto createCommentDto) {
        Comment comment = Comment.builder()
                .content(createCommentDto.getContent())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .isAnonymous(createCommentDto.getIsAnonymous())
                .build();

        return comment;
    }
}
