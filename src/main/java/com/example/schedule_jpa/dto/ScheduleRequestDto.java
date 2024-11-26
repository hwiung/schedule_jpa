package com.example.schedule_jpa.dto;

import lombok.Getter;

@Getter
public class ScheduleRequestDto {               // 일정 생성 또는 업데이트 요청 데이터를 캡슐화하는 DTO 클래스.

    private final Long userId;                      // 어떤 사용자가 해당 일정을 생성했는지 확인하기 위한 필드.
    private final String title;                     // 클라이언트가 요청 시 제공하는 일정의 제목을 저장하는 필드.
    private final String content;                   // 일정의 상세 내용을 저장하는 필드.

    public ScheduleRequestDto(Long userId, String title, String content) {      // 객체 생성 시, userId, title, content 필드를 초기화.
        this.userId = userId;                                                       // 매개변수 userId 값을 현재 객체의 userId 필드에 할당.
        this.title = title;                                                         // 매개변수 title 값을 현재 객체의 title 필드에 할당.
        this.content = content;                                                     // 매개변수 content 값을 현재 객체의 content 필드에 할당.
    }
}