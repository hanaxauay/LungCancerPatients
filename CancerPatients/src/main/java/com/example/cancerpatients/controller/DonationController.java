package com.example.cancerpatients.controller;

import com.example.cancerpatients.dto.DonationDto;
import com.example.cancerpatients.entity.Donation;
import com.example.cancerpatients.service.DonationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;

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

    @GetMapping("/donation_detail/{seq}")
    public String showDonationDetail(@PathVariable Long seq, Model model) {
        Optional<Donation> optionalDonation = donationService.getDonationById(seq);

        if (optionalDonation.isPresent()) {
            Donation donation = optionalDonation.get();
            model.addAttribute("donation", donation);

        }
        return "donation_detail";
//        else {
//            // 존재하지 않는 게시물에 대한 처리
//            return "error";
//        }
    }

    @GetMapping("/gallery_detail_1")
    public String showGallery_Detail(Model model) {

        return "gallery_detail_1";
//        else {
//            // 존재하지 않는 게시물에 대한 처리
//            return "error";
//        }
    }

    @GetMapping("/donation_write")
    public String showWriteDonationForm(Model model) {
        model.addAttribute("donationDto", new DonationDto());
        return "donation_write";
    }

    @PostMapping("/donation_write")
    public String writeDonation(@ModelAttribute DonationDto donationDto) {
        donationService.savePost(donationDto);
        return "redirect:/donation_manage";
    }


    // 기존 게시물 수정 페이지 보기
    @GetMapping("/donation_edit/{id}")
    public String showEditDonationForm(@PathVariable Long id, Model model) {
        Optional<Donation> donationDto = donationService.getDonationById(id);
        model.addAttribute("donation", donationDto.orElse(new Donation())); // orElse는 Optional이 비어있을 때 기본값을 설정합니다.
        return "donation_edit";
    }

    @PostMapping("/donation_edit/{id}")
    public String updateDonation(@PathVariable Long id, DonationDto donationDto) {
        donationService.updateDonation(id, donationDto);
        return "redirect:/donation_manage";
    }
}
