package com.example.cancerpatients.controller;

import com.example.cancerpatients.dto.DonationDto;
import com.example.cancerpatients.entity.Donation;
import com.example.cancerpatients.service.DonationService;
import org.springframework.data.domain.Sort;
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

    @GetMapping("/donation")
    public String donation(Model model) {
        List<DonationDto> donationList = donationService.getDonationList(Sort.by(Sort.Direction.DESC, "seq"));
        model.addAttribute("donationList", donationList);

        return "donation";
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

    @GetMapping("/donation_detail_1")
    public String goGreeting22(){
        return "donation_detail_1";
    }


    @GetMapping("/donation_detail_2")
    public String goGr232eeting(){
        return "donation_detail_2";
    }

    @GetMapping("/donation_write")
    public String showWriteDonationForm(Model model) {
        model.addAttribute("donationDto", new DonationDto());
        return "donation_write";
    }

    @PostMapping("/donation_write")
    public String writeDonation(@ModelAttribute DonationDto donationDto) {
        donationService.savePost(donationDto);
        return "redirect:/donation";
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
        return "redirect:/donation";
    }


    @GetMapping("/donation_delete/{id}")
    public String deleteDonation(@PathVariable Long id) {
        donationService.deleteDonation(id);

        // 글 삭제 후 donation.seq를 -1로 설정
        Donation deletedDonation = new Donation();
        deletedDonation.setSeq(-1L);

        return "redirect:/donation";
    }

}
