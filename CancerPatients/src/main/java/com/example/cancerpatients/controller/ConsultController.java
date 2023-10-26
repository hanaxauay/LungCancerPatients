package com.example.cancerpatients.controller;

import com.example.cancerpatients.dto.ConsultDto;
import com.example.cancerpatients.entity.Consult;
import com.example.cancerpatients.service.ConsultService;
import org.springframework.http.ResponseEntity;
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

    @GetMapping("/consult_list_edit/{seq}")
    public String edit(@PathVariable Long seq, Model model) {
        ConsultDto consultDto = consultService.getConsultBySeq(seq);
        model.addAttribute("consult", consultDto);
        return "consult_list_edit";
    }

    @PostMapping("/updateMemo")
    public ResponseEntity<String> updateMemo(@RequestParam("seq") Long seq, @RequestParam("newMemo") String newMemo) {
        Consult updatedConsult = consultService.updateMemo(seq, newMemo);
        if (updatedConsult != null) {
            return ResponseEntity.ok("메모가 업데이트되었습니다.");
        } else {
            return ResponseEntity.badRequest().body("해당 상담 정보를 찾을 수 없습니다.");
        }
    }
}