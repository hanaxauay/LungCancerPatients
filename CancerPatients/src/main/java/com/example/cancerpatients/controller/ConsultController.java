package com.example.cancerpatients.controller;

import com.example.cancerpatients.dto.ConsultDto;
import com.example.cancerpatients.dto.DonationDto;
import com.example.cancerpatients.entity.Donation;
import com.example.cancerpatients.service.ConsultService;
import com.example.cancerpatients.service.DonationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;

@Controller
public class ConsultController {
    private ConsultService consultService;

    public ConsultController(ConsultService consultService) {
        this.consultService = consultService;
    }


    @GetMapping("/list_consult")
    public String list(Model model) {
        List<ConsultDto> consultDtoList = consultService.getConsultList();
        model.addAttribute("consultList", consultDtoList);
        return "list_consult";
    }

    @PostMapping("/consult")
    public String write(ConsultDto consultDto) {
        consultService.savePost(consultDto);
        return "consult_done";
    }
}