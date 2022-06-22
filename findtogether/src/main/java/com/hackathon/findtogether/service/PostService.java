package com.hackathon.findtogether.service;


import com.hackathon.findtogether.domain.Post;
import com.hackathon.findtogether.domain.User;
import com.hackathon.findtogether.dto.request.CreatePostDto;
import com.hackathon.findtogether.dto.request.UpdatePostDto;
import com.hackathon.findtogether.repository.PostRepository;
import com.hackathon.findtogether.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public Post findOne(Long id){
        return postRepository.findOne(id);
    }

    // 내가 올린 게시글 조회
    public List<Post> findPostByUsername(String username){
//        String username = SecurityUtil.getCurrentUsername().get();
        return postRepository.findPostsByUsername(username);
    }

    // 전체 게시글 조회
    public List<Post> findAllPost(){
        return postRepository.findAll();
    }

    // 게시글 등록
    @Transactional
    public Long savePost(CreatePostDto createPostDto){

        // 사용자 정보 가져오기핍
        User user = userRepository.findByUsername("보핍").get();

        //게시물 생성
        Post post = Post.createPost(createPostDto,user);
        postRepository.save(post);

        return post.getId();
    }

    // 게시물 수정
    @Transactional
    public void updatePost(Long postId, UpdatePostDto updatePostDto) {
        Post post = postRepository.findOne(postId);
        post.updatePost(updatePostDto);
        postRepository.save(post);
    }

    // 게시물 삭제
    @Transactional
    public void removePost(Long postId){
        Post post = postRepository.findOne(postId);
        postRepository.remove(post);
    }

    // 게시물 검색
//    public List<SearchResultDto> searchPost(PostSearchDto postSearchDto){
//        return postSearchRespository.search(postSerachDto);
//        return null;
//    }
}
