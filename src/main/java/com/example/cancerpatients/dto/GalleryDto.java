package com.example.cancerpatients.dto;

import com.example.cancerpatients.entity.Gallery;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class GalleryDto {
    private Long seq;
    private String title;
    private String content;
    private String author;
    private LocalDate write_time;
    private String filePath; // 파일 경로 추가
    private String fileName; // 파일 이름 추가

    public Gallery toEntity() {
        return Gallery.builder()
                .seq(seq)
                .title(title)
                .content(content)
                .write_time(write_time)
                .filePath(filePath)
                .fileName(fileName)
                .build();
    }

    @Builder
    public GalleryDto(Long seq, String title, String content, LocalDate write_time, String filePath, String fileName) {
        this.seq = seq;
        this.title = title;
        this.content = content;
        this.write_time = write_time;
        this.filePath = filePath;
        this.fileName = fileName;
    }
}
