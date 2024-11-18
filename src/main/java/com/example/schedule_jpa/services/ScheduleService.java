package com.example.schedule_jpa.services;

import com.example.schedule_jpa.entities.Schedule;
import com.example.schedule_jpa.entities.User;
import com.example.schedule_jpa.exceptions.CustomException;
import com.example.schedule_jpa.repositories.ScheduleRepository;
import com.example.schedule_jpa.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final UserRepository userRepository;

    public Schedule createSchedule(String title, String content, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new CustomException("유효하지 않은 요청입니다."));
        Schedule schedule = Schedule.builder()
                .title(title)
                .content(content)
                .user(user)
                .build();
        return scheduleRepository.save(schedule);
    }

    public Schedule getSchedule(Long id) {
        return scheduleRepository.findById(id)
                .orElseThrow(() -> new CustomException("일정을 찾을 수 없습니다."));
    }
}
