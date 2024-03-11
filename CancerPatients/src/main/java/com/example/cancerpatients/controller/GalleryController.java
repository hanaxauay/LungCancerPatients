package com.example.cancerpatients.controller;

import com.example.cancerpatients.dto.GalleryDto;
import com.example.cancerpatients.entity.Gallery;
import com.example.cancerpatients.service.GalleryService;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Controller
public class GalleryController {
    private final GalleryService galleryService;

    public GalleryController(GalleryService galleryService) {
        this.galleryService = galleryService;
    }
    @GetMapping("/gallery")
    public String gallery(Model model) {
        List<GalleryDto> galleryList = galleryService.getGalleryList(Sort.by(Sort.Direction.DESC, "seq"));
        model.addAttribute("galleryList", galleryList);

        return "gallery";
    }

    @GetMapping("/gallery_write")
    public String showGalleryForm(Model model) {
        model.addAttribute("galleryDto", new GalleryDto());
        return "gallery_write";
    }

    @PostMapping("/gallery_write")
    public String writeGallery(@ModelAttribute GalleryDto galleryDto,
                               @RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            // 파일이 업로드되지 않은 경우 처리
            return "redirect:/gallery_write?error=file";
        }

        try {
            // 파일 저장 경로 설정
            String uploadDir = "uploads/";
            String fileName = file.getOriginalFilename();
            String filePath = uploadDir + fileName;
            File dest = new File(filePath);
            file.transferTo(dest);

            // 파일 경로를 GalleryDto에 추가
            galleryDto.setFilePath(filePath);
            galleryDto.setFileName(fileName);

            // GalleryDto를 서비스를 통해 저장
            galleryService.savePost(galleryDto);

            return "redirect:/gallery";
        } catch (IOException e) {
            e.printStackTrace();
            // 파일 업로드 실패 시 처리
            return "redirect:/gallery_write?error=upload";
        }
    }

    @GetMapping("/gallery_detail/{seq}")
    public String showGalleryDetail(@PathVariable Long seq, Model model) {
        Optional<Gallery> optionalGallery = galleryService.getGalleryById(seq);

        if (optionalGallery.isPresent()) {
            Gallery gallery = optionalGallery.get();
            model.addAttribute("gallery", gallery);
        }
        return "gallery_detail";
        //        else {
        //            // 존재하지 않는 게시물에 대한 처리
        //            return "error";
        //        }
    }

    // 기존 게시물 수정 페이지 보기
    @GetMapping("/gallery_edit/{id}")
    public String showEditGalleryForm(@PathVariable Long id, Model model) {
        Optional<Gallery> galleryDto = galleryService.getGalleryById(id);
        model.addAttribute("gallery", galleryDto.orElse(new Gallery())); // orElse는 Optional이 비어있을 때 기본값을 설정합니다.
        return "gallery_edit";
    }

    @PostMapping("/gallery_edit/{id}")
    public String updateGallery(@PathVariable Long id, GalleryDto galleryDto) {
        galleryService.updateGallery(id, galleryDto);
        return "redirect:/gallery";
    }

    @GetMapping("/gallery_delete/{id}")
    public String deleteGallery(@PathVariable Long id) {
        galleryService.deleteGallery(id);

        // 글 삭제 후 gallery.seq를 -1로 설정
        Gallery deletedGallery = new Gallery();
        deletedGallery.setSeq(-1L);

        return "redirect:/gallery";
    }
}
