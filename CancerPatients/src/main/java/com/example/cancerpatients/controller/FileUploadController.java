package com.example.cancerpatients.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("/common/*")
public class FileUploadController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping(value = "ckUpload", method = RequestMethod.POST)
    @ResponseBody
    public void ckUpload(HttpServletRequest req, HttpServletResponse res, @RequestParam MultipartFile upload) throws Exception {
        logger.info("upload 들어온다! ");

        res.setCharacterEncoding("utf-8");
        res.setContentType("text/html; charset=utf-8");

        //파일 이름 가져오기
        String fileName = upload.getOriginalFilename();

        //파일을 바이트 배열로 변환
        byte[] bytes = upload.getBytes();

        //이미지를 업로드할 디렉토리를 정해준다
        String uploadPath = "/Users/ihana/Documents/GitHub/LungCancerPatients/CancerPatients/src/main/resources/static/ckUpload/";

        OutputStream out = new FileOutputStream(new File(uploadPath + fileName));

        //서버에 write
        out.write(bytes);

        //성공여부 가져오기
        String callback = req.getParameter("CKEditorFuncNum");

        //클라이언트에 이벤트 추가 (자바스크립트 실행)
        PrintWriter printWriter = res.getWriter(); //자바스크립트 쓰기위한 도구

        String fileUrl = req.getContextPath() + "/ckUpload/" + fileName;

        if (callback != null && !callback.equals("1")) { // callback이 1일 경우만 성공한 것
            Map<String, String> responseMap = new HashMap<>();
            responseMap.put("uploaded", "true");
            responseMap.put("url", fileUrl);
            ObjectMapper objectMapper = new ObjectMapper();
            String jsonResponse = objectMapper.writeValueAsString(responseMap);

            // JSON 응답 전송
            printWriter = res.getWriter();
            printWriter.write(jsonResponse);


        } else {
            logger.info("upload img 들어온다! " + fileUrl);
            Map<String, String> responseMap = new HashMap<>();
            responseMap.put("uploaded", "true");
            responseMap.put("url", fileUrl);
            ObjectMapper objectMapper = new ObjectMapper();
            String jsonResponse = objectMapper.writeValueAsString(responseMap);

            // JSON 응답 전송
            printWriter = res.getWriter();
            printWriter.write(jsonResponse);


        }

        printWriter.flush();

    }
}