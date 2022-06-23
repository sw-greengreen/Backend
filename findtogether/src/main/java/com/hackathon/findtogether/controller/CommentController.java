package com.hackathon.findtogether.controller;

import com.hackathon.findtogether.domain.Comment;
import com.hackathon.findtogether.dto.request.CreateCommentDto;
import com.hackathon.findtogether.dto.request.UpdateCommentDto;
import com.hackathon.findtogether.dto.response.CommentUserDto;
import com.hackathon.findtogether.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
public class CommentController {

    private final CommentService commentService;

    //댓글 등록
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/post/{postId}/comment")
    public Response createComment(@RequestBody @Valid CreateCommentDto createCommentDto) throws Exception {
        Long commentId = commentService.saveComment(createCommentDto);
        Comment comment = commentService.findOne(commentId);
        return new Response(201,true,"created comment successfully", comment);
    }

    //댓글 전체 조회
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/post/{postId}/comment")
    public Response getAllComment(@PathVariable Long postId) throws Exception {
//        List<Comment> comments = commentService.findAllComment(postId);

        List<Comment> comments = commentService.findAllCommentWithUser(postId);
        List<CommentUserDto> commentsDto = comments.stream()
                .map(o -> new CommentUserDto(o))
                .collect(Collectors.toList());

        if (comments == null)
            return new Response(404,false,"not found comments", "");
        return new Response(200,true,"found comments successfully", commentsDto);
    }

    //댓글 수정
    @PutMapping("/post/{postId}/comment/{commentId}")
    public Response updateComment(@PathVariable Long postId,
                                  @PathVariable Long commentId,
                               @RequestBody @Valid UpdateCommentDto updateCommentDto) {

        commentService.updateComment(postId, commentId, updateCommentDto);
        Comment comment = commentService.findOne(commentId);
        return new Response(200,true,"update comment successfully", comment);
    }

    //댓글 삭제
    @DeleteMapping("/post/{postId}/comment/{commentId}")
    public Response deletePost(@PathVariable Long postId,
                               @PathVariable Long commentId) {

        commentService.removeComment(postId, commentId);
        return new Response(200,true,"delete comment successfully", commentId);
    }
}
