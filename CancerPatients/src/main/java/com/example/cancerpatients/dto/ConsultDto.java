package com.example.cancerpatients.dto;

import com.example.cancerpatients.entity.Consult;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor
public class ConsultDto {
    private Long seq;
    private String author;
    private String title;
    private String content;
    private String phone;
    private String memo;



    public Consult toEntity() {
        Consult build = Consult.builder()
                .seq(seq)
                .author(author)
                .title(title)
                .content(content)
                .phone(phone)
                .memo(memo)
                .build();
        return build;
    }

    @Builder
    public ConsultDto(Long seq, String author, String title, String content, String phone, String memo) {
        this.seq = seq;
        this.author = author;
        this.title = title;
        this.content = content;
        this.phone = phone;
        this.memo = memo;
    }
}