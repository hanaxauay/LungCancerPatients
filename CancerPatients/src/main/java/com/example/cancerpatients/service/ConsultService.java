package com.example.cancerpatients.service;

import com.example.cancerpatients.entity.Consult;
import com.example.cancerpatients.repository.ConsultRepository;
import com.example.cancerpatients.dto.ConsultDto;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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


    public Consult updateMemo(Long seq, String newMemo) {
        Consult consult = consultRepository.findById(seq).orElse(null);
        if (consult != null) {
            consult.setMemo(newMemo);
            return consultRepository.save(consult);
        }
        return null;
    }
    @Transactional
    public ConsultDto getConsultBySeq(Long seq) {
        Consult consult = consultRepository.findBySeq(seq);
        if (consult != null) {
            return ConsultDto.builder()
                    .seq(consult.getSeq())
                    .author(consult.getAuthor())
                    .title(consult.getTitle())
                    .content(consult.getContent())
                    .phone(consult.getPhone())
                    .memo(consult.getMemo())
                    .build();
        }
        return null; // 혹시 해당 seq로 찾지 못한 경우 null을 반환하거나 다른 처리를 수행할 수 있습니다.
    }
}