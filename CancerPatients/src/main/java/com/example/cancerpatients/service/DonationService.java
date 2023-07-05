package com.example.cancerpatients.service;

import com.example.cancerpatients.dto.DonationDto;
import com.example.cancerpatients.repository.DonationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DonationService {
    @Autowired
    private DonationRepository donationRepository;

    public List<DonationDto> selectAll(){
        return donationRepository.findAll();
    }

    public DonationDto selectOne(int seq){
        return donationRepository.findBySeq(seq);
    }
}