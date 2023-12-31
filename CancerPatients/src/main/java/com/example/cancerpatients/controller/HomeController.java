package com.example.cancerpatients.controller;

import com.example.cancerpatients.dto.DonationDto;
import com.example.cancerpatients.service.DonationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@Slf4j
public class HomeController {
    private final DonationService donationService;

    public HomeController(DonationService donationService) {

        this.donationService = donationService;
    }

    @GetMapping("/")
    public String home(Model model){

        List<DonationDto> donationList = donationService.getDonationList();
        model.addAttribute("donationList", donationList);
        model.addAttribute("donationTest", "Test");
        System.out.println("donationList: " + donationList);

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

    @GetMapping("/notice")
    public String goNotice(){
        return "notice";
    }

    @GetMapping("/gallery")
    public String goGallery(){
        return "gallery";
    }

    @GetMapping("/donation_write")
    public String goDonation_write(){
        return "donation_write";
    }

    @PostMapping("/donation_write")
    public String goDonation_write(DonationDto donationDto){
        donationService.savePost(donationDto);
        return "redirect:/";
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
