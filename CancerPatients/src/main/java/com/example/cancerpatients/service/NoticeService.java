package com.example.cancerpatients.service;

import com.example.cancerpatients.dto.NoticeDto;
import com.example.cancerpatients.entity.Notice;
import com.example.cancerpatients.repository.NoticeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class NoticeService {
    @Autowired
    private NoticeRepository noticeRepository;

    public NoticeService(NoticeRepository noticeRepository) {
        this.noticeRepository = noticeRepository;
    }

    @Transactional
    public Long savePost(NoticeDto noticeDto) {
        Notice notice = Notice.builder()
                .title(noticeDto.getTitle())
                .content(noticeDto.getContent())
                .author(noticeDto.getAuthor())
                .write_time(LocalDate.from(LocalDateTime.now())) // 현재 시간으로 설정
                .build();

        return noticeRepository.save(notice).getSeq();
    }

 @Transactional
    public void updateNotice(Long id, NoticeDto noticeDto) {
        Optional<Notice> optionalNotice = noticeRepository.findById(id);

        if (optionalNotice.isPresent()) {
            Notice existingNotice = optionalNotice.get();

            existingNotice.setTitle(noticeDto.getTitle());
            existingNotice.setContent(noticeDto.getContent());
            existingNotice.setAuthor(noticeDto.getAuthor());

            // 기타 필요한 업데이트 작업 수행

            // 업데이트된 기부 내용을 저장
            noticeRepository.save(existingNotice);
        } else {
            throw new RuntimeException("공지사항을 찾을 수 없습니다: " + id);
        }
    }

    public void deleteNotice(Long id) {
        noticeRepository.deleteById(id);
    }


    @Transactional
    public List<NoticeDto> getNoticeList(){
        List<Notice> noticeList = noticeRepository.findAll();
        List<NoticeDto> noticeDtoList = new ArrayList<>();

        for(Notice notice : noticeList) {
            NoticeDto noticeDto = NoticeDto.builder()
                    .seq(notice.getSeq())
                    .title(notice.getTitle())
                    .content(notice.getContent())
                    .author(notice.getAuthor())
                    .write_time(notice.getWrite_time())
                    .build();
            noticeDtoList.add(noticeDto);

        }
        return noticeDtoList;
    }

    public List<Notice> getAllNotice() {
        return noticeRepository.findAll();
    }

    public Optional<Notice> getNoticeById(Long seq) {
        return noticeRepository.findById(seq);
    }
}