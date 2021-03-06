package com.hackathon.findtogether.controller;

import com.hackathon.findtogether.domain.Post;
import com.hackathon.findtogether.domain.PostType;
import com.hackathon.findtogether.domain.User;
import com.hackathon.findtogether.dto.request.CreatePostDto;
import com.hackathon.findtogether.dto.request.UpdatePostDto;
import com.hackathon.findtogether.service.PostService;
import com.hackathon.findtogether.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@RestController
@Slf4j
public class PostController {

    private final PostService postService;
    private final UserService userService;

    // 게시물 등록
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/api/v1/post")
    public Response createPost(@RequestBody @Valid CreatePostDto createPostDto) throws Exception{
        Long id = postService.savePost(createPostDto);
        Post post = postService.findOne(id);
        User user = post.getUser();

        if (post.getPostType() == PostType.LOST)
            userService.downgradePointUser(user.getId());

        if (user.getPointcount() >= 5)
            userService.updateAchievementDetecter(user.getId());
        else if (user.getPointcount() > -5 && user.getPointcount() < 5)
            userService.updateAchievementBasic(user.getId());
        else if (user.getPointcount() <= -5)
            userService.updateAchievementLoser(user.getId());

        return new Response(201,true,"created post successfully", post);

    }

    // 게시물 전체 조회
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/api/v1/post/all")
    public Response getPostByAll() throws Exception{
        List<Post> posts = postService.findAllPost();
        if (posts == null)
            return new Response(404,false,"not found post", "");
        return new Response(200,true,"found post successfully", posts);
    }

    // 게시물 조회 by post id
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/api/v1/post/{id}")
    public Response getPostByUsername(@PathVariable Long id) throws Exception{
        Post post = postService.findOne(id);
        if (post == null)
            return new Response(404,false,"not found post", "");
        return new Response(200,true,"found post successfully", post);
    }

    // 게시물 수정
    @PutMapping("/api/v1/post/{id}")
    public Response updatePost(@PathVariable Long id,
                               @RequestBody @Valid UpdatePostDto updatePostDto) throws Exception{
        postService.updatePost(id, updatePostDto);
        Post post = postService.findOne(id);
        return new Response(200,true,"update post successfully", post);
    }

    // 게시물 삭제
    @DeleteMapping("/api/v1/post/{id}")
    public Response deletePost(@PathVariable Long id) throws Exception{
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
