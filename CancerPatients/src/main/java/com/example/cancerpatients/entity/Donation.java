package com.example.cancerpatients.entity;

import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Entity
@EntityListeners(AuditingEntityListener.class) /*JPA에게 해당 entity는 auditiong기능을 사용함을 알린다*/
@NoArgsConstructor
public class Donation {
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
    private LocalDate write_time;

    @Column(name="VIEWS")
    private int views;


    @Builder
    public Donation(Long seq, String title, String content, String author, LocalDate write_time, int views) {
        this.seq = seq;
        this.title = title;
        this.content = content;
        this.author = author;
        this.write_time = write_time;
        this.views = views;
    }
}