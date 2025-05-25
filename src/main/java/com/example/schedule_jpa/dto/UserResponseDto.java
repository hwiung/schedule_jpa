package com.example.schedule_jpa.dto;

import com.example.schedule_jpa.entity.User;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class UserResponseDto {  // 사용자 조회 결과를 클라이언트에 반환하기 위한 DTO 클래스.

    private final Long id;
    private final String userName;
    private final String email;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;

    // DTO 객체 생성 시 필드(id, userName, email, createdAt, modifiedAt)를 초기화.
    public UserResponseDto(Long id, String userName, String email, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.id = id;
        this.userName = userName;
        this.email = email;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }

    // User 엔티티 객체를 UserResponseDto로 변환하는 정적 메서드.
    public static UserResponseDto toDto(User user) {
        return new UserResponseDto(
                user.getId(),
                user.getUserName(),
                user.getEmail(),
                user.getCreatedAt(),
                user.getModifiedAt()
        );
    }
}
