package com.example.cancerpatients.controller;

import com.example.cancerpatients.dto.NoticeDto;
import com.example.cancerpatients.entity.Notice;
import com.example.cancerpatients.service.NoticeService;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;

@Controller
public class NoticeController {
    private final NoticeService noticeService;

    public NoticeController(NoticeService noticeService) {
        this.noticeService = noticeService;
    }

    @GetMapping("/notice")
    public String notice(Model model) {
        List<NoticeDto> noticeList = noticeService.getNoticeList(Sort.by(Sort.Direction.DESC, "seq"));
        model.addAttribute("noticeList", noticeList);

        return "notice";
    }


    @GetMapping("/notice_write")
    public String showNoticeGalleryForm(Model model) {
        model.addAttribute("noticeDto", new NoticeDto());
        return "notice_write";
    }

    @PostMapping("/notice_write")
    public String writeNotice(@ModelAttribute NoticeDto noticeDto) {
        noticeService.savePost(noticeDto);
        return "redirect:/notice";
    }

    @GetMapping("/notice_detail/{seq}")
    public String showNoticeDetail(@PathVariable Long seq, Model model) {
        Optional<Notice> optionalNotice = noticeService.getNoticeById(seq);

        if (optionalNotice.isPresent()) {
            Notice notice = optionalNotice.get();
            model.addAttribute("notice", notice);

        }
        return "notice_detail";
//        else {
//            // 존재하지 않는 게시물에 대한 처리
//            return "error";
//        }
    }


    // 기존 게시물 수정 페이지 보기
    @GetMapping("/notice_edit/{id}")
    public String showEditNoticeForm(@PathVariable Long id, Model model) {
        Optional<Notice> noticeDto = noticeService.getNoticeById(id);
        model.addAttribute("notice", noticeDto.orElse(new Notice())); // orElse는 Optional이 비어있을 때 기본값을 설정합니다.
        return "notice_edit";
    }

    @PostMapping("/notice_edit/{id}")
    public String updateNotice(@PathVariable Long id, NoticeDto noticeDto) {
        noticeService.updateNotice(id, noticeDto);
        return "redirect:/notice";
    }


    @GetMapping("/notice_delete/{id}")
    public String deleteNotice(@PathVariable Long id) {
        noticeService.deleteNotice(id);

        // 글 삭제 후 donation.seq를 -1로 설정
        Notice deletedNotice = new Notice();
        deletedNotice.setSeq(-1L);

        return "redirect:/notice";
    }

}
