package com.example.cancerpatients.repository;

import com.example.cancerpatients.entity.Donation;
import com.example.cancerpatients.entity.Notice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoticeRepository extends JpaRepository<Notice,Long> {

    List<Notice> findAllByOrderBySeqDesc();
}
