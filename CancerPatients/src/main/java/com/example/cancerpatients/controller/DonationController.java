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

    @GetMapping("/detail/{postId}")
    public String showPostDetail(@PathVariable Long seq, Model model) {
        // postId를 사용하여 게시물 정보를 데이터베이스에서 가져오거나 서비스를 통해 데이터를 조회합니다.
        DonationDto donation = donationService.getDonationById(seq);

        if (donation == null) {
            // 게시물이 없을 경우 에러 처리
            return "error"; // 에러 페이지로 리다이렉트 또는 에러 처리 로직을 추가
        }

        // 모델에 데이터를 추가하여 HTML 템플릿에 전달
        model.addAttribute("donation", donation);

        return "detail"; // 상세 정보 페이지로 이동
    }


}
