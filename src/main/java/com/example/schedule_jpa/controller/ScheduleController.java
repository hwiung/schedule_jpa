package com.example.schedule_jpa.controller;

import com.example.schedule_jpa.dto.ScheduleRequestDto;
import com.example.schedule_jpa.dto.ScheduleResponseDto;
import com.example.schedule_jpa.dto.ScheduleUpdateRequestDto;
import com.example.schedule_jpa.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController                     // json 또는 xml 형식의 응답을 반환함.
@RequiredArgsConstructor                // final로 선언된 모든 필드에 생성자를 자동으로 생성해주고 이를 통해서 의존성을 주입 받는다.
public class ScheduleController {
    private final ScheduleService scheduleService;      // final은 객체가 변경되지 않음을 보장함.

    @GetMapping("/schedules")   // GET 요청 처리 -> schedules 엔드포인트를 호출하면 모든 일정을 조회함.
    public ResponseEntity<List<ScheduleResponseDto>> findAll() {    // HTTP 상태 코드와 함께 응답 본문에 모든 일정 데이터를 포함하여 반환함.
        return ResponseEntity.ok().body(scheduleService.findAll()); // 서비스 계층에서 모든 일정 데이터를 가져옴.
    }

    @GetMapping("/schedules/{id}")          // GET 요청을 처리 -> 특정 ID에 해당하는 일정을 조회함.
    public ResponseEntity<ScheduleResponseDto> findOne(@PathVariable Long id) {     // URL 경로에 포함된 ID 값을 메서드 매개변수로 받아옴
        return ResponseEntity.ok().body(scheduleService.findById(id));      // 서비스 계층에서 해당 ID에 해당하는 일정 데이터를 가져옴, 200(ok)코드 반환.
    }

    @DeleteMapping("/schedules/{id}")       // DELETE 요청을 처리 -> 특정 ID에 해당하는 일정을 삭제함.
    public ResponseEntity<String> deleteSchedule(@PathVariable Long id) {       // 서비스 계층을 호출하여 해당 ID의 일정을 삭제함.
        scheduleService.deleteSchedule(id);
        return ResponseEntity.ok().body("삭제됐습니다.");
    }

    @PostMapping("/schedules")      // POST 요청을 처리하며, 새로운 일정을 생성함.
    public ResponseEntity<ScheduleResponseDto> createdSchedule(@RequestBody ScheduleRequestDto scheduleRequestDto) {
        //  요청 본문에 포함된 JSON 데이터를 ScheduleRequestDto 객체로 변환하여 받아옴.
        return ResponseEntity.status(HttpStatus.CREATED).body(scheduleService.createSchedule(scheduleRequestDto));
        //  서비스 계층에서 일정을 생성하는 메서드를 호출함.
    }

    @PatchMapping("/schedules/{id}")    // PATCH 요청을 처리하며, 특정 ID에 해당하는 일정의 일부를 수정함.
    public ResponseEntity<ScheduleResponseDto> updateSchedule(@RequestBody ScheduleUpdateRequestDto updateRequestDto,
                                                                // 요청 본문에 포함된 JSON 데이터를 ScheduleUpdateRequestDto 객체로 변환하여 받아옴.
                                                              @PathVariable Long id) {
                                                                // URL 경로에 포함된 ID 값을 메서드 매개변수로 받아옴.
        return ResponseEntity.ok().body(scheduleService.updateSchedule(id, updateRequestDto));  // 서비스 계층을 호출하여 해당 ID의 일정을 업데이트함.
    }
}
