package com.hackathon.findtogether.service;

import com.hackathon.findtogether.domain.*;
import com.hackathon.findtogether.dto.request.CreateAlarmDto;
import com.hackathon.findtogether.dto.request.CreateCommentDto;
import com.hackathon.findtogether.dto.request.UpdateCommentDto;
import com.hackathon.findtogether.repository.AlarmRepository;
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
    private final AlarmRepository alarmRepository;

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

        // 댓글 등록시 게시자에게 알림 생성
        User postWriter = userRepository.findOne(post.getUser().getId());

        CreateAlarmDto alarmDto = CreateAlarmDto.builder()
                .username(postWriter.getUsername())
                .alarmType(AlarmType.COMMENT)
                .alarmStatus(1)
                .message("["+post.getTitle()+"]"+" 게시물에 새로운 댓글이 등록되었어요!")
                .build();

        Alarm alarm = Alarm.createAlarm(alarmDto,postWriter);
        alarmRepository.save(alarm);

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
