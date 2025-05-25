package com.example.schedule_jpa.dto;

import com.example.schedule_jpa.entity.Schedule;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ScheduleResponseDto {                // 일정 데이터를 클라이언트로 응답할 때 사용되는 DTO 클래스.
    private final Long id;  // 일정 고유 식별자(데이터베이스에서 각 일정 레코드를 식별하기 위한 유일한 값)
    private final Long userId; // 일정 작성자의 고유 식별자
    private final String title; // 일정의 제목
    private final String content; // 일정의 내용
    private final LocalDateTime createdAt; // 일정 생성 시간
    private final LocalDateTime modifiedAt; // 일정 수정 시간

    // 객체 생성 시 모든 필드를 초기화.
    public ScheduleResponseDto(Long id, Long userId, String title, String content, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.id = id;   // 매개변수 id 값을 클래스의 id 필드에 할당.
        this.userId = userId;  // 매개변수 userId 값을 클래스의 userId 필드에 할당.
        this.title = title;  // 매개변수 title 값을 클래스의 title 필드에 할당.
        this.content = content;  // 매개변수 content 값을 클래스의 content 필드에 할당.
        this.createdAt = createdAt;   // 매개변수 createdAt 값을 클래스의 createdAt 필드에 할당.
        this.modifiedAt = modifiedAt;   // 매개변수 modifiedAt 값을 클래스의 modifiedAt 필드에 할당.
    }

    public static ScheduleResponseDto toDto(Schedule schedule) {   // Schedule 엔티티를 ScheduleResponseDto로 변환하는 정적 메서드이며,
                                                                        // 서비스 계층에서 엔티티 데이터를 클라이언트 응답 DTO로 변환할 때 사용.
        return new ScheduleResponseDto(
                schedule.getId(),   // 엔티티의 id 값을 DTO의 id로 설정.
                schedule.getUser().getId(),     // 엔티티의 user 필드에서 id 값을 가져와 DTO의 userId로 설정.
                schedule.getTitle(),        // 엔티티의 title 값을 DTO의 title로 설정.
                schedule.getContent(),      // 엔티티의 content 값을 DTO의 content로 설정.
                schedule.getCreatedAt(),        // 엔티티의 createdAt 값을 DTO의 createdAt으로 설정.
                schedule.getModifiedAt()        // 엔티티의 modifiedAt 값을 DTO의 modifiedAt으로 설정.
        );
    }
}
