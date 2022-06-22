package com.hackathon.findtogether.controller;

import com.hackathon.findtogether.service.FileService;
import com.hackathon.findtogether.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.FileNotFoundException;
import java.io.IOException;

@Slf4j
@RestController
@RequiredArgsConstructor
public class FileController {

    private final FileService fileService;
    private final PostService postService;

    @PostMapping(value = "/upload")
    public Response uploadFile(MultipartFile file) {
        if(file.isEmpty()) {
            /* 파일을 업로드 하지 않았을 경우 처리 */
        }

        String fileName = fileService.saveFile(file);
//        String downloadURI = ServletUriComponentsBuilder.fromCurrentContextPath()
//                .path("/download/")
//                .path(file.getName())
//                .toUriString();

        return new Response(200,true,"created image successfully", fileName);
    }


//    @GetMapping(value = "/download")
//    public ResponseEntity<?> downloadFile(String fileName) throws FileNotFoundException {
//
//        Resource resource = fileService.loadFile(fileName);
//
//        String contentType = null;
//        try {
//            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
//        } catch (IOException ex) {
//            log.info("Could not determine file type.");
//        }
//
//        // Fallback to the default content type if type could not be determined
//        if(contentType == null) {
//            contentType = "application/octet-stream";
//        }
//        return ResponseEntity.ok()
//                .contentType(MediaType.parseMediaType(contentType))
//                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
//                .body(resource);
//    }
}