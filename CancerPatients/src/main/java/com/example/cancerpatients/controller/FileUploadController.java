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

    private String uploadPath = "/Users/ihana/Documents/GitHub/LungCancerPatients/CancerPatients/src/main/resources/static/images";

    @RequestMapping(value = "ckUpload", method = RequestMethod.POST)
    @ResponseBody
    public void ckUpload(HttpServletRequest req, HttpServletResponse res, @RequestParam MultipartFile upload) throws Exception {
        logger.info("ckUpload 진입 =========================================1");

        // 랜덤 문자 생성
        UUID uid = UUID.randomUUID();

        OutputStream out = null;
        PrintWriter printWriter = null;

        // 인코딩
        res.setCharacterEncoding("utf-8");
        res.setContentType("application/json;charset=utf-8");

        try {

            String fileName = upload.getOriginalFilename();  // 파일 이름 가져오기
            byte[] bytes = upload.getBytes();

            // 업로드 경로
            String ckUploadPath = uploadPath + File.separator + "ckUpload" + File.separator + uid + "_" + fileName;

            File uploadDir = new File(uploadPath + File.separator + "ckUpload");
            if (!uploadDir.exists()) {
                uploadDir.mkdirs();
            }

            out = new FileOutputStream(new File(ckUploadPath));
            out.write(bytes);
            out.flush();  // out에 저장된 데이터를 전송하고 초기화

            String fileUrl = "/ckUpload/" + uid + "_" + fileName;  // 작성화면

            // JSON 응답 구성
            Map<String, String> responseMap = new HashMap<>();
            responseMap.put("uploaded", "true");
            responseMap.put("url", fileUrl);

            // JSON 문자열로 변환
            ObjectMapper objectMapper = new ObjectMapper();
            String jsonResponse = objectMapper.writeValueAsString(responseMap);

            // JSON 응답 전송
            printWriter = res.getWriter();
            printWriter.write(jsonResponse);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (printWriter != null) {
                    printWriter.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
