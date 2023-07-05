package com.example.cancerpatients.controller;

import com.example.cancerpatients.dto.DonationDto;
import com.example.cancerpatients.service.DonationService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@Slf4j
public class HomeController {

    @Autowired
    private DonationService donationService;

    public Logger logger = LoggerFactory.getLogger(Controller.class);
    @GetMapping("/")
    public String home(){
        return "index.html";
    }

    @GetMapping("/organization")
    public String goOrganization(){
        return "organization.html";
    }
    @GetMapping("/greeting")
    public String goGreeting(){
        return "greeting.html";
    }

    @GetMapping("/donation_manage")
    public String godonation_manage(Model model){
        // 모든 후원금 내역 조회
        List<DonationDto> donations = donationService.selectAll();

        // 조회된 엔티티들을 모델에 담아서 뷰로 전달
        model.addAttribute("donations",donations);
        return "donation_manage.html";
    }
    @GetMapping("/tree")
    public String gotree(){
        return "tree.html";
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
