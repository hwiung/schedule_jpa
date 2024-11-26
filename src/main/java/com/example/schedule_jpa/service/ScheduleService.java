package com.example.schedule_jpa.service;

import com.example.schedule_jpa.dto.ScheduleRequestDto;
import com.example.schedule_jpa.dto.ScheduleResponseDto;
import com.example.schedule_jpa.dto.ScheduleUpdateRequestDto;
import com.example.schedule_jpa.entity.Schedule;
import com.example.schedule_jpa.entity.User;
import com.example.schedule_jpa.repository.ScheduleRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    // ScheduleRepository와 UserService에 의존성 주입을 하여 사용
    private final ScheduleRepository scheduleRepository;
    private final UserService userService;

    public List<ScheduleResponseDto> findAll() {
        List<Schedule> schedules = scheduleRepository.findAll();

        // 엔티티를 DTO로 변환하여 반환(List<Schedule> -> List<ScheduleResponseDto>)
        return schedules
                .stream()
                .map(ScheduleResponseDto::toDto)
                .toList();
    }

    // 특정 ID의 스케줄 조회(ID를 기반으로 스케줄을 조회하고 ScheduleResponseDto로 변환)
    public ScheduleResponseDto findById(Long id) {
        return ScheduleResponseDto.toDto(findScheduleById(id));
    }

    // 스케줄 ID로 Schedule 엔티티를 조회(존재하지 않는 경우 IllegalArgumentException 예외 발생)
    private Schedule findScheduleById(Long id) {
        return scheduleRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("ID가 잘못됐습니다."));
    }

    // 스케줄 삭제(스케줄 ID를 기반으로 조회한 후 데이터베이스에서 삭제)
    public void deleteSchedule(Long id) {
        findScheduleById(id);
        scheduleRepository.deleteById(id);
    }

    @Transactional      // 트랜잭션 관리: 데이터의 일관성과 원자성을 보장

    // 새로운 스케줄 생성(UserService를 통해 유저를 조회하고 스케줄을 생성 및 저장)
    public ScheduleResponseDto createSchedule(ScheduleRequestDto scheduleRequestDto) {
        User user = userService.findUserById(scheduleRequestDto.getUserId());
        Schedule schedule = new Schedule(user, scheduleRequestDto.getTitle(), scheduleRequestDto.getContent());

        Schedule savedSchedule = scheduleRepository.save(schedule); // 디비에 저장.
        return ScheduleResponseDto.toDto(savedSchedule);    // 저장된 스케줄을 DTO로 변환하여 반환
    }

    @Transactional
    public ScheduleResponseDto updateSchedule(Long id, ScheduleUpdateRequestDto updateRequestDto) {
        Schedule schedule = findScheduleById(id);
        schedule.update(updateRequestDto.getTitle(), updateRequestDto.getContent());
        return ScheduleResponseDto.toDto(schedule);     // 업데이트된 스케줄을 DTO로 변환하여 반환
    }
}

