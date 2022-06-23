package com.hackathon.findtogether.service;

import com.hackathon.findtogether.domain.Post;
import com.hackathon.findtogether.dto.request.PostSearchDto;
import com.hackathon.findtogether.repository.PostRepository;
import com.hackathon.findtogether.repository.PostSearchRepository;
import com.hackathon.findtogether.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PostSearchService {

    private final PostRepository postRepository;
    private final PostSearchRepository postSearchRepository;

    // 찾아주세요 게시글 조회
    public List<Post> findPostByLost(){
        return postSearchRepository.findPostByLost();
    }

    // 찾았어요 게시글 조회
    public List<Post> findPostByDiscovery(){
        return postSearchRepository.findPostByDiscovery();
    }

    // 해시태그 또는 제목으로 조회
    public List<Post> findPostByKeyword(String keyword){
        return postSearchRepository.findPostByKeyword(keyword);
    }
    // 최신순, 조회순, 해결, 미해결로 조회

}
