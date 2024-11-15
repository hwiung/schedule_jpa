package com.example.schedule_jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/schedules")
public class ScheduleController {

    private final ScheduleService scheduleService;

    @Autowired
    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    // 일정 생성
    @PostMapping
    public ResponseEntity<ScheduleDto> createSchedule(@RequestBody @Valid ScheduleDto scheduleDto) {
        // DTO를 엔티티로 변환하고 저장
        Schedule schedule = scheduleService.saveSchedule(scheduleDto.toEntity());
        // 저장된 엔티티를 다시 DTO로 변환하여 반환
        return ResponseEntity.ok(ScheduleDto.fromEntity(schedule));
    }

    // 특정 일정 조회
    @GetMapping("/{id}")
    public ResponseEntity<ScheduleDto> getSchedule(@PathVariable Long id) {
        Schedule schedule = scheduleService.findScheduleById(id);
        // 엔티티를 DTO로 변환하여 반환
        return ResponseEntity.ok(ScheduleDto.fromEntity(schedule));
    }

    // 모든 일정 조회
    @GetMapping
    public ResponseEntity<List<ScheduleDto>> getAllSchedules() {
        List<Schedule> schedules = scheduleService.findAllSchedules();
        // 엔티티 리스트를 DTO 리스트로 변환
        List<ScheduleDto> scheduleDtos = schedules.stream()
                .map(ScheduleDto::fromEntity)
                .toList();
        return ResponseEntity.ok(scheduleDtos);
    }

    // 일정 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSchedule(@PathVariable Long id) {
        scheduleService.deleteSchedule(id);
        return ResponseEntity.noContent().build();
    }
}
