package com.example.cancerpatients.controller;

import com.example.cancerpatients.dto.GalleryDto;
import com.example.cancerpatients.entity.Gallery;
import com.example.cancerpatients.service.GalleryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

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
        List<GalleryDto> galleryList = galleryService.getGalleryList();
        model.addAttribute("galleryList", galleryList);
        model.addAttribute("galleryTest", "Test");
        System.out.println("galleryList: " + galleryList);

        return "gallery";
    }

    @GetMapping("/gallery_write")
    public String showGalleryForm(Model model) {
        model.addAttribute("galleryDto", new GalleryDto());
        return "gallery_write";
    }

    @PostMapping("/gallery_write")
    public String writeGallery(@ModelAttribute GalleryDto galleryDto) {
        galleryService.savePost(galleryDto);
        return "redirect:/gallery";
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
