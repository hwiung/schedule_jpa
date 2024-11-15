package com.example.schedule_jpa;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

@Entity
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 기본 키

    @NotBlank(message = "제목은 공백일 수 없습니다.")
    private String title; // 할일 제목

    private String content; // 할일 내용 (단수형으로 수정)

    @ManyToOne // 다대일 관계 설정
    @JoinColumn(name = "user_id", nullable = false) // 외래 키 user_id로 연결, null 허용 안 함
    private User user; // 작성한 유저

    @CreatedDate
    private LocalDateTime createDate; // 생성일 자동 관리

    @LastModifiedDate
    private LocalDateTime modifiedDate; // 수정일 자동 관리

    // Getter & Setter (Lombok이 없다면 직접 추가)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public LocalDateTime getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(LocalDateTime modifiedDate) {
        this.modifiedDate = modifiedDate;
    }
}
