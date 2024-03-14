package com.example.cancerpatients.service;

import com.example.cancerpatients.dto.DonationDto;
import com.example.cancerpatients.entity.Donation;
import com.example.cancerpatients.repository.DonationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DonationService {
    @Autowired
    private DonationRepository donationRepository;

    public DonationService(DonationRepository donationRepository) {
        this.donationRepository = donationRepository;
    }

    @Transactional
    public Long savePost(DonationDto donationDto) {
        Donation donation = Donation.builder()
                .title(donationDto.getTitle())
                .content(donationDto.getContent())
                .write_time(LocalDate.from(LocalDateTime.now())) // 현재 시간으로 설정
                .views(0) // 초기 조회수 0으로 설정
                .build();

        return donationRepository.save(donation).getSeq();
    }

 @Transactional
    public void updateDonation(Long id, DonationDto donationDto) {
        Optional<Donation> optionalDonation = donationRepository.findById(id);

        if (optionalDonation.isPresent()) {
            Donation existingDonation = optionalDonation.get();

            existingDonation.setTitle(donationDto.getTitle());
            existingDonation.setContent(donationDto.getContent());
            // 업데이트된 기부 내용을 저장
            donationRepository.save(existingDonation);
        } else {
            throw new RuntimeException("기부 내용을 찾을 수 없습니다: " + id);
        }
    }

    public void deleteDonation(Long id) {
        donationRepository.deleteById(id);
    }

    @Transactional
    public List<DonationDto> getDonationList(Sort sort){
        List<Donation> donationList = donationRepository.findAll(sort);
        List<DonationDto> donationDtoList = new ArrayList<>();

        for(Donation donation : donationList) {
            DonationDto donationDto = DonationDto.builder()
                    .seq(donation.getSeq())
                    .title(donation.getTitle())
                    .content(donation.getContent())
                    .write_time(donation.getWrite_time())
                    .views(donation.getViews())
                    .build();
            donationDtoList.add(donationDto);

        }
        return donationDtoList;
    }

    public List<Donation> getAllDonations() {
        return donationRepository.findAll();
    }

    public Optional<Donation> getDonationById(Long seq) {
        return donationRepository.findById(seq);
    }

}