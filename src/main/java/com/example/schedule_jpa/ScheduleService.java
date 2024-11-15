package com.example.schedule_jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;

    @Autowired
    public ScheduleService(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    // 일정 저장
    public Schedule saveSchedule(Schedule schedule) {
        // 비즈니스 로직: 저장 전에 유효성 검증
        if (schedule.getTitle() == null || schedule.getTitle().isBlank()) {
            throw new IllegalArgumentException("일정의 제목은 필수입니다.");
        }
        return scheduleRepository.save(schedule);
    }

    // 특정 일정 조회
    public Schedule findScheduleById(Long id) {
        // Optional 처리 후 Schedule 반환
        return scheduleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("일정을 찾을 수 없습니다."));
    }

    // 모든 일정 조회
    public List<Schedule> findAllSchedules() {
        // 전체 일정 반환
        return scheduleRepository.findAll();
    }

    // 일정 삭제
    public void deleteSchedule(Long id) {
        // 삭제 전에 존재 여부 확인
        if (!scheduleRepository.existsById(id)) {
            throw new IllegalArgumentException("삭제할 일정이 존재하지 않습니다.");
        }
        scheduleRepository.deleteById(id);
    }
}
