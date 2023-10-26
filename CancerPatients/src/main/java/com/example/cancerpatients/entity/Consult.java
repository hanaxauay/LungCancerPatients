package com.example.cancerpatients.entity;

import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
public class Consult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SEQ", nullable = false)
    private Long seq;

    @Column(name = "AUTHOR", nullable = false, length = 20)
    private String author;

    @Column(name = "TITLE", nullable = false, length = 100)
    private String title;

    @Column(name = "CONTENT", nullable = false, columnDefinition = "TEXT")
    private String content;

    @Column(name = "PHONE", nullable = false, length = 20)
    private String phone;

    @Column(name = "MEMO", columnDefinition = "TEXT")
    private String memo;


    @Builder
    public Consult(Long seq, String author, String title, String content, String phone, String memo) {
        this.seq = seq;
        this.author = author;
        this.title = title;
        this.content = content;
        this.phone = phone;
        this.memo = memo;
    }
    // Constructors, getters, and setters
}
