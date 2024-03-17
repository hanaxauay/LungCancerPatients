package com.example.cancerpatients.repository;

import com.example.cancerpatients.entity.Consult;
import com.example.cancerpatients.entity.Donation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsultRepository extends JpaRepository<Consult,Long> {
    Consult findBySeq(Long seq);


}
