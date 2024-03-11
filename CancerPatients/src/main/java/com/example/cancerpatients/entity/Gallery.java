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
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
public class Gallery {
    @Id
    @Column(name="SEQ", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seq;

    @Column(name="TITLE", nullable = false)
    private String title;

    @Column(name="CONTENT", nullable = false)
    private String content;

    @Column(name="AUTHOR", nullable = false)
    private String author;

    @Column(name="WRITE_TIME")
    private LocalDate write_time;

    @Column(name="FILE_PATH") // 파일 경로 필드 추가
    private String filePath;

    @Column(name="FILE_NAME") // 파일 이름 필드 추가
    private String fileName;

    @Builder
    public Gallery(Long seq, String title, String content, String author, LocalDate write_time, String filePath, String fileName) {
        this.seq = seq;
        this.title = title;
        this.content = content;
        this.author = author;
        this.write_time = write_time;
        this.filePath = filePath;
        this.fileName = fileName;
    }
}
