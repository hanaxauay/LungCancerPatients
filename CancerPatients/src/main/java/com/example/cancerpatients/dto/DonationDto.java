package com.example.cancerpatients.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Table(name="DONATION")
@Entity

public class DonationDto {
    @Id
    @Column(name="SEQ",nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY) //기본 키 생성을 데이터베이스에 위임->즉, id 값을 null로 하면 DB가 알아서 AUTO_INCREMENT 해준다.
    private Long seq;

    private String title;

    private String content;

    private String author;

    private LocalDat
    private int views;


}
