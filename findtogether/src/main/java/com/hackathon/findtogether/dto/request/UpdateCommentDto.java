package com.hackathon.findtogether.dto.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UpdateCommentDto {

//    private String writer; //댓글 작성자
    private String content;
    private LocalDateTime updatedAt;
    private String isAnonymous;
}
