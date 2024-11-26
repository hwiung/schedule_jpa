package com.example.schedule_jpa.dto;

import lombok.Getter;

@Getter
public class ScheduleUpdateRequestDto {         // 클라이언트로부터 일정 업데이트 요청 데이터를 수신하기 위한 DTO 클래스.
    private final String title;
    private final String content;

    public ScheduleUpdateRequestDto(String title, String content) {     // 객체를 생성할 때 필수 데이터(title, content)를 초기화.

        this.title = title;
        this.content = content;
    }
}

