package com.example.cancerpatients.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;


@Table(name="DONATION")
@Entity
@Getter
public class DonationDto {
    @Id
    @Column(name="SEQ",nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY) //기본 키 생성을 데이터베이스에 위임->즉, id 값을 null로 하면 DB가 알아서 AUTO_INCREMENT 해준다.
    private Long seq;

    @Column(name="TITLE",nullable = false)
    private String title;

    @Column(name="CONTENT",nullable = false)
    private String content;

    @Column(name="AUTHOR",nullable = false)
    private String author;

    @Column(name="WRITE_TIME")
    private LocalDateTime write_time;

    @Column(name="VIEWS")
    private int views;


}
