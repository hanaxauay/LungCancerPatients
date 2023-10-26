package com.example.cancerpatients.service;

import com.example.cancerpatients.entity.Consult;
import com.example.cancerpatients.repository.ConsultRepository;
import com.example.cancerpatients.dto.ConsultDto;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class ConsultService {
    private ConsultRepository consultRepository;

    public ConsultService(ConsultRepository consultRepository) {
        this.consultRepository = consultRepository;
    }

    @Transactional
    public Long savePost(ConsultDto consultDto) {
        return consultRepository.save(consultDto.toEntity()).getSeq();
    }

    @Transactional
    public List<ConsultDto> getConsultList() {
        List<Consult> consultList = consultRepository.findAll();
        List<ConsultDto> consultDtoList = new ArrayList<>();

        for(Consult consult : consultList) {
            ConsultDto consultDto = ConsultDto.builder()
                    .seq(consult.getSeq())
                    .author(consult.getAuthor())
                    .title(consult.getTitle())
                    .content(consult.getContent())
                    .phone(consult.getPhone())
                    .memo(consult.getMemo())
                    .build();
            consultDtoList.add(consultDto);
        }
        return consultDtoList;
    }
}