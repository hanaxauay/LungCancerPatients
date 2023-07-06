package com.example.cancerpatients.controller;

import com.example.cancerpatients.entity.Donation;
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


    @GetMapping("/")
    public String home(){
        return "index";
    }

    @GetMapping("/organization")
    public String goOrganization(){
        return "organization";
    }
    @GetMapping("/greeting")
    public String goGreeting(){
        return "greeting";
    }


    @GetMapping("/tree")
    public String gotree(){
        return "tree";
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
