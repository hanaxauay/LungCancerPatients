package com.example.cancerpatients.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/fileupload")
public class FileUploadController {

    @PostMapping
    public String handleFileUpload(@RequestParam("file") MultipartFile file) {
        // 파일을 저장하고 저장된 파일의 URL을 반환하는 로직을 작성
        // 예시로 파일을 저장하지 않고 파일의 이름을 그대로 반환하는 코드
        return file.getOriginalFilename();
    }
}