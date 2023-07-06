package com.example.cancerpatients.dto;

import com.example.cancerpatients.entity.Donation;
import lombok.*;

import javax.persistence.Column;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class DonationDto {
    private Long seq;
    private String title;
    private String content;
    private String author;
    private LocalDateTime write_time;
    private int views;

    public Donation toEntity(){
        Donation build = Donation.builder()
                .seq(seq)
                .title(title)
                .author(author)
                .write_time(write_time)
                .views(views)
                .build();
        return build;
    }

    @Builder
    public DonationDto(Long seq, String title, String content, String author, LocalDateTime write_time, int views) {
        this.seq = seq;
        this.title = title;
        this.content = content;
        this.author = author;
        this.write_time=write_time;
        this.views=views;
    }
}
