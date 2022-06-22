package com.hackathon.findtogether.controller;

import com.hackathon.findtogether.domain.Post;
import com.hackathon.findtogether.dto.request.CreatePostDto;
import com.hackathon.findtogether.dto.request.UpdatePostDto;
import com.hackathon.findtogether.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.lang.management.LockInfo;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    // 게시물 등록
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/api/v1/post")
    public Response createPost(@RequestBody @Valid CreatePostDto createPostDto){
        Long id = postService.savePost(createPostDto);
        Post post = postService.findOne(id);
        return new Response(201,true,"created post successfully", post);
    }

    // 게시물 조회 by post id
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/api/v1/post/{id}")
    public Response getPostByUsername(@PathVariable Long id){
        Post post = postService.findOne(id);
        return new Response(200,true,"found post successfully", post);
    }

    // 게시물 수정
    @PutMapping("/api/v1/post/{id}")
    public Response updatePost(@PathVariable Long id,
                               @RequestBody @Valid UpdatePostDto updatePostDto) {
        postService.updatePost(id, updatePostDto);
        Post post = postService.findOne(id);
        return new Response(200,true,"update post successfully", post);
    }

    // 게시물 삭제
    @DeleteMapping("/api/v1/post/{id}")
    public Response deletePost(@PathVariable Long id){
        postService.removePost(id);
        return new Response(200,true,"delete post successfully", id);
    }

    //게시물 조회
//    @GetMapping("/api/v1/post/search")
//    public Response searchPosts(@RequestParam(value="region") String region,
//                                @RequestParam(value="keyword") String keyword,
//                                @RequestParam(value="sorting") String sorting){
//        PostSerachDto postSerachDto = PostSerachDto.builder()
//                .region(region)
//                .keyword(keyword)
//                .sortType(sorting)
//                .build();
//        List<SearchResultDto> posts = postService.searchPost(postSerachDto);
//        return new Response(200,true,"get posts successfully", posts);
//    }
}
