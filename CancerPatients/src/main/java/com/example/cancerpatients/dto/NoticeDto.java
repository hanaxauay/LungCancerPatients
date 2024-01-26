package com.example.cancerpatients.dto;

import com.example.cancerpatients.entity.Notice;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class NoticeDto {
    private Long seq;
    private String title;
    private String content;
    private String author;
    private LocalDate write_time;

    public Notice toEntity(){
        Notice build = Notice.builder()
                .seq(seq)
                .title(title)
                .author(author)
                .write_time(write_time)
                .build();
        return build;
    }

    @Builder
    public NoticeDto(Long seq, String title, String content, String author, LocalDate write_time) {
        this.seq = seq;
        this.title = title;
        this.content = content;
        this.author = author;
        this.write_time=write_time;
    }
}
