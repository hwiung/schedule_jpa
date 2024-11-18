package com.example.schedule_jpa.controllers;

import com.example.schedule_jpa.dtos.schedule.ScheduleRequestDTO;
import com.example.schedule_jpa.entities.Schedule;
import com.example.schedule_jpa.services.ScheduleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/schedules")
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    @PostMapping
    public ResponseEntity<?> createSchedule(@RequestBody @Valid ScheduleRequestDTO requestDTO) {
        Schedule schedule = scheduleService.createSchedule(
                requestDTO.getTitle(),
                requestDTO.getContent(),
                requestDTO.getUserId()
        );
        return ResponseEntity.ok(Map.of(
                "id", schedule.getId(),
                "title", schedule.getTitle(),
                "content", schedule.getContent(),
                "userId", schedule.getUser().getId(),
                "createdData", schedule.getCreatedDate(),
                "modifiedDate", schedule.getModifiedDate()
        ));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getSchedule(@PathVariable Long id) {
        Schedule schedule = scheduleService.getSchedule(id);
        return ResponseEntity.ok(Map.of(
                "id", schedule.getId(),
                "title", schedule.getTitle(),
                "content", schedule.getContent(),
                "userId", schedule.getUser().getId(),
                "createdData", schedule.getCreatedDate(),
                "modifiedDate", schedule.getModifiedDate()
        ));
    }
}
