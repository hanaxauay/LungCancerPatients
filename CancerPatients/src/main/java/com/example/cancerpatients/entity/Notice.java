package com.example.cancerpatients.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@EntityListeners(AuditingEntityListener.class) /*JPA에게 해당 entity는 auditiong기능을 사용함을 알린다*/
@NoArgsConstructor
public class Notice {
    @Id
    @Column(name="SEQ",nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY) //기본 키 생성을 데이터베이스에 위임->즉, id 값을 null로 하면 DB가 알아서 AUTO_INCREMENT 해준다.
    private Long seq;

    @Column(name="TITLE",nullable = false)
    private String title;

    @Column(name="CONTENT",nullable = false)
    private String content;

    @Column(name="WRITE_TIME")
    private LocalDate write_time;


    @Builder
    public Notice(Long seq, String title, String content, LocalDate write_time) {
        this.seq = seq;
        this.title = title;
        this.content = content;
        this.write_time = write_time;
    }
}