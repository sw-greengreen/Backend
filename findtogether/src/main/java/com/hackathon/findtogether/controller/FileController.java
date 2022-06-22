package com.hackathon.findtogether.controller;

import com.hackathon.findtogether.service.FileService;
import com.hackathon.findtogether.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


@Slf4j
@RestController
@RequiredArgsConstructor
public class FileController {

    private final FileService fileService;
    private final PostService postService;

    @PostMapping(value = "/api/v1/file")
    public Response uploadFile(MultipartFile file) {
        if(file.isEmpty()) {
            /* 파일을 업로드 하지 않았을 경우 처리 */
        }
        String fileName = fileService.saveFile(file);

        return new Response(200,true,"created image successfully", fileName);
    }
}