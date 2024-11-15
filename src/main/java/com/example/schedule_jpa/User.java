package com.example.schedule_jpa;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 고유 식별자

    @NotBlank
    private String username; // 유저명 필드

    @NotBlank
    @Email
    private String email; // 이메일

    @NotBlank
    private String password; // 비밀번호 필드

    @CreatedDate
    private LocalDateTime createdDate; // 생성일 자동 관리

    @LastModifiedDate
    private LocalDateTime modifiedDate; // 수정일 자동 관리
}
