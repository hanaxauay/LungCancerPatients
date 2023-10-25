package com.example.cancerpatients.service;

import com.example.cancerpatients.dto.DonationDto;
import com.example.cancerpatients.entity.Donation;
import com.example.cancerpatients.repository.DonationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class DonationService {
    @Autowired
    private DonationRepository donationRepository;

    public DonationService(DonationRepository donationRepository) {
        this.donationRepository = donationRepository;
    }

//    @Transactional
//    public Long savePost(DonationDto donationDto) {
//        return donationRepository.save(donationDto.toEntity()).getSeq();
//    }

    @Transactional
    public List<DonationDto> getDonationList(){
        List<Donation> donationList = donationRepository.findAll();
        List<DonationDto> donationDtoList = new ArrayList<>();

        for(Donation donation : donationList) {
            DonationDto donationDto = DonationDto.builder()
                    .seq(donation.getSeq())
                    .title(donation.getTitle())
                    .content(donation.getContent())
                    .author((donation.getAuthor()))
                    .write_time(donation.getWrite_time())
                    .views(donation.getViews())
                    .build();
            donationDtoList.add(donationDto);

        }
        return donationDtoList;
    }




//    public List<Donation> selectAll(){
//        return donationRepository.findAll();
//    }
//
//    public Donation selectOne(int seq){
//        return donationRepository.findBySeq(seq);
//    }
}