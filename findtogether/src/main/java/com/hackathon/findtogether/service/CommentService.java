package com.hackathon.findtogether.service;

import com.hackathon.findtogether.domain.Comment;
import com.hackathon.findtogether.domain.Post;
import com.hackathon.findtogether.domain.User;
import com.hackathon.findtogether.dto.request.CreateCommentDto;
import com.hackathon.findtogether.dto.request.UpdateCommentDto;
import com.hackathon.findtogether.repository.CommentRepository;
import com.hackathon.findtogether.repository.PostRepository;
import com.hackathon.findtogether.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public Comment findOne(Long commentId) {
        return commentRepository.findOnefindOneWithCommendId(commentId);
    }

    //하나의 게시글에 있는 모든 댓글 조회
    public List<Comment> findAllComment(Long post_id) {
        return commentRepository.findAll(post_id);
    }

    //댓글 등록
    @Transactional
    public Long saveComment(CreateCommentDto createCommentDto) {
        //작성자 정보 가져오기
        User writer = userRepository.findByUsername(createCommentDto.getWriter()).get();
        //게시글 정보 가져오기
        Post post = postRepository.findOne(createCommentDto.getPostId());

        Comment comment = Comment.createComment(createCommentDto, post, writer);
        commentRepository.save(comment);
        return comment.getId();
    }

    //댓글 수정
    @Transactional
    public void updateComment(Long postId, Long commentId, UpdateCommentDto updateCommentDto) {
        Comment comment = commentRepository.findOneWithPostIdAndCommendId(postId, commentId);
        comment.updateComment(updateCommentDto);
        commentRepository.save(comment);
    }

    //댓글 삭제
    @Transactional
    public void removeComment(Long postId, Long commentId) {
        Comment comment = commentRepository.findOneWithPostIdAndCommendId(postId, commentId);
        commentRepository.remove(comment);
    }
}
