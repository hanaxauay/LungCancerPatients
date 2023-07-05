package com.example.cancerpatients.repository;

import com.example.cancerpatients.dto.DonationDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DonationRepository extends JpaRepository<DonationDto,Long> {
    List<DonationDto> findAll();

    DonationDto findBySeq(int seq);
}
