package com.example.cancerpatients.controller;

import com.example.cancerpatients.dto.ConsultDto;
import com.example.cancerpatients.entity.Consult;
import com.example.cancerpatients.service.ConsultService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ConsultController {
    private ConsultService consultService;

    public ConsultController(ConsultService consultService) {
        this.consultService = consultService;
    }


    @GetMapping("/consult_list")
    public String list(Model model) {
        List<ConsultDto> consultDtoList = consultService.getConsultList();
        model.addAttribute("consultList", consultDtoList);
        return "consult_list";
    }

    @PostMapping("/consult")
    public String write(ConsultDto consultDto) {
        consultService.savePost(consultDto);
        return "consult_done";
    }


    //    @PostMapping("/consult_list_edit")
//    public String updateMemo(@RequestParam("seq") Long seq, @RequestParam("newMemo") String newMemo) {
//        consultService.updateMemo(seq, newMemo);
//        return "redirect:/consult_list"; // 메모 업데이트 후 상담 목록 페이지로 리다이렉트
//    }
    @GetMapping("/consult_list_edit")
    public String edit(@PathVariable("seq") Long seq, Model model) {
        ConsultDto consultDto = consultService.getConsultBySeq(seq);
        model.addAttribute("consult", consultDto);
        return "consult_list_edit";
    }
}