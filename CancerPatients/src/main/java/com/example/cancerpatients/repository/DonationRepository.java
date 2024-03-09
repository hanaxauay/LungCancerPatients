package com.example.cancerpatients.repository;

import com.example.cancerpatients.entity.Donation;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DonationRepository extends JpaRepository<Donation,Long> {
    List<Donation> findAllByOrderBySeqDesc();

}
