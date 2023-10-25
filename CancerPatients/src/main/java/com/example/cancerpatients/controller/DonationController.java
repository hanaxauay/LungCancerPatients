package com.example.cancerpatients.controller;

import com.example.cancerpatients.dto.DonationDto;
import com.example.cancerpatients.entity.Donation;
import com.example.cancerpatients.service.DonationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class DonationController {
    private final DonationService donationService;

    public DonationController(DonationService donationService) {
        this.donationService = donationService;
    }

    @GetMapping("/donation_manage")
    public String donationManage(Model model) {
        List<DonationDto> donationList = donationService.getDonationList();
        model.addAttribute("donationList", donationList);
        model.addAttribute("donationTest", "Test");
        System.out.println("donationList: " + donationList);



        return "donation_manage";
    }


}
