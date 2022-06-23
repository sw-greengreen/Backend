package com.hackathon.findtogether.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hackathon.findtogether.dto.request.CreateCommentDto;
import com.hackathon.findtogether.dto.request.UpdateCommentDto;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Comment {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    @JsonIgnore
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User writer;

    @Column(nullable = false)
    private String content;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updatedAt;

    private String isAnonymous;

    //==댓글 생성 메서드==//
    public static Comment createComment(CreateCommentDto createCommentDto, Post post, User user){
        Comment comment = Comment.builder()
                .post(post)
                .writer(user)
                .content(createCommentDto.getContent())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .isAnonymous(createCommentDto.getIsAnonymous())
                .build();

        return comment;
    }

    //==댓글 수정 메서드==//
    public void updateComment(UpdateCommentDto updateCommentDto) {
//        private String writer; //댓글 작성자
//        private String content;
        this.content = updateCommentDto.getContent();
        this.updatedAt = LocalDateTime.now();
        this.isAnonymous = updateCommentDto.getIsAnonymous();
    }
}
