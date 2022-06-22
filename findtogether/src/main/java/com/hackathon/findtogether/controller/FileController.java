package com.hackathon.findtogether.controller;

import com.hackathon.findtogether.service.FileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Slf4j
@RestController
@RequiredArgsConstructor
public class FileController {

    private final FileService fileService;

    @PostMapping(value = "/upload")
    public Response uploadFile(MultipartFile file) {
        if(file.isEmpty()) {
            /* 파일을 업로드 하지 않았을 경우 처리 */
        }

//        String downloadURI = ServletUriComponentsBuilder.fromCurrentContextPath()
//                .path("/download/")
//                .path(file.getName())
//                .toUriString();
        fileService.saveFile(file);
        return new Response(200,true,"created image successfully", "");
    }

    @GetMapping(value = "/download")
    public Response downloadFile(String fileName) {
        return new Response(200,true,"created image successfully", fileName);
    }
}