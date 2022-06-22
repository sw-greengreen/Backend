package com.hackathon.findtogether.service;

import com.hackathon.findtogether.domain.File;
import com.hackathon.findtogether.util.FileUploadProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
public class FileService {
    private final Path dirLocation;

    @Autowired
    public FileService(FileUploadProperties fileUploadProperties) {
        this.dirLocation = Paths.get(fileUploadProperties.getLocation())
                .toAbsolutePath().normalize();
    }

    @PostConstruct
    public void init() {
        try {
            Files.createDirectories(this.dirLocation);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //설정한 경로로 파일 업로드
    public String saveFile(MultipartFile multipartFile) {

        String fileName = multipartFile.getOriginalFilename();
        Path location = this.dirLocation.resolve(fileName);
        try {
            /* 실제 파일이 upload 되는 부분 */
            Files.copy(multipartFile.getInputStream(), location, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return fileName;
    }

//    // 파일명으로 파일 찾고 리소스 반환
//    public Resource loadFile(String fileName) throws FileNotFoundException {
//
//        try {
//            Path file = this.dirLocation.resolve(fileName).normalize();
//            Resource resource = new UrlResource(file.toUri());
//
//            if(resource.exists() || resource.isReadable()) {
//                return resource;
//            }else {
//                throw new FileNotFoundException("Could not find file");
//            }
//        } catch (MalformedURLException e) {
//            throw new FileNotFoundException("Could not download file");
//        }
}