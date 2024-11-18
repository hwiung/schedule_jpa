package com.example.schedule_jpa.dtos.schedule;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ScheduleRequestDTO {

    @NotBlank
    private String title;

    private String content;

    @NotNull
    private Long userId;
}
