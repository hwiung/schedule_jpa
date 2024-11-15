package com.example.schedule_jpa;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ScheduleDto {
    private Long id; // 일정 ID

    @NotBlank(message = "제목은 비워둘 수 없습니다.")
    private String title; // 일정 제목

    private String content; // 일정 내용

    // DTO -> 엔티티 변환
    public Schedule toEntity() {
        Schedule schedule = new Schedule();
        schedule.setTitle(this.title);
        schedule.setContent(this.content);
        return schedule;
    }

    // 엔티티 -> DTO 변환
    public static ScheduleDto fromEntity(Schedule schedule) {
        ScheduleDto dto = new ScheduleDto();
        dto.setId(schedule.getId());
        dto.setTitle(schedule.getTitle());
        dto.setContent(schedule.getContent());
        return dto;
    }
}
