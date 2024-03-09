package com.example.cancerpatients.controller;

import com.example.cancerpatients.dto.DonationDto;
import com.example.cancerpatients.dto.NoticeDto;
import com.example.cancerpatients.service.DonationService;
import com.example.cancerpatients.service.NoticeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@Slf4j
public class HomeController {
    private final NoticeService noticeService;

    public HomeController(NoticeService noticeService) {

        this.noticeService = noticeService;
    }

    @GetMapping("/")
    public String home(Model model){

        List<NoticeDto> noticeList = noticeService.getNoticeList();
        model.addAttribute("noticeList", noticeList);
        model.addAttribute("noticeTest", "Test");
        System.out.println("noticeList: " + noticeList);

        return "index";
    }

    @GetMapping("/organization")
    public String goOrganization(){
        return "organization";
    }
    @GetMapping("/trace")
    public String goTrace(){
        return "trace";
    }
    @GetMapping("/greeting")
    public String goGreeting(){
        return "greeting";
    }


    @GetMapping("/consult")
    public String goConsult(){
        return "consult";
    }

    @GetMapping("/breath_walk")
    public String goBreath_walk(){
        return "breath_walk";
    }

    @GetMapping("/breath_program")
    public String goBreath_program(){
        return "breath_program";
    }

    @GetMapping("/activity")
    public String goActivity(){
        return "activity";
    }


    @GetMapping("/tree")
    public String goTree(){
        return "tree";
    }

    @GetMapping("/question")
    public String goQuestion(){
        return "question";
    }



//    @GetMapping("/dbTest")
///   public List<DonationDto> dbTest() {
//    public ResponseEntity<String> dbTest(){
//
//        logger.info("Controller getMember()");
///       return donationService.getDonationList();
//        Map<String,String> result = donationService.getDonationList().get(0);
//
//        return ResponseEntity.ok(result.toString());
//    }
}
