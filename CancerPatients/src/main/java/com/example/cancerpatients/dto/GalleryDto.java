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

    public Gallery toEntity(){
        Gallery build = Gallery.builder()
                .seq(seq)
                .title(title)
                .author(author)
                .write_time(write_time)
                .build();
        return build;
    }

    @Builder
    public GalleryDto(Long seq, String title, String content, String author, LocalDate write_time) {
        this.seq = seq;
        this.title = title;
        this.content = content;
        this.author = author;
        this.write_time=write_time;
    }
}
