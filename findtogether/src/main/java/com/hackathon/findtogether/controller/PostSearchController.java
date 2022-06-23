package com.hackathon.findtogether.controller;

import com.hackathon.findtogether.domain.Post;
import com.hackathon.findtogether.service.PostSearchService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@Slf4j
public class PostSearchController {

    private final PostSearchService postSearchService;

    // 게시물 조회
    // LOST, DISCOVERY
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/api/v1/posts")
    public Response getPostByLost(@RequestParam(value="postType") String postType){

        // 찾아주세요
        if (postType.equals("LOST")) {
            List<Post> posts = postSearchService.findPostByLost();
            return new Response(200,true,"found post successfully", posts);
        }

        // 찾았어요
        else{
            List<Post> posts = postSearchService.findPostByDiscovery();
            return new Response(200,true,"found post successfully", posts);
        }

    }

    // 게시물 조회
    // 제목 또는 해시태그
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/api/v1/search/posts")
    public Response getPostByKeyword(@RequestParam(value="keyword") String keyword){
        List<Post> posts =postSearchService.findPostByKeyword(keyword);
        return new Response(200,true,"found post successfully", posts);
    }

    // 게시물 조회
    // 최신순, 오래된순, 해결, 미해결
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/api/v1/sorting/posts")
    public Response getPostBySorting(@RequestParam(value="sortType") String sortType){
        List<Post> posts =postSearchService.findPostBySortType(sortType);
        return new Response(200,true,"found post successfully", posts);
    }
}
