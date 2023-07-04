package com.example.cancerpatients.controller;

import com.example.cancerpatients.dto.DonationDto;
//import com.example.cancerpatients.service.DonationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@Controller
@Slf4j

public class HomeController {
//
//    @Autowired
//    private DonationService donationService;

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
    public String godonation_manage(){
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
