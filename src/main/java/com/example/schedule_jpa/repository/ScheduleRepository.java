package com.example.schedule_jpa.repository;

import com.example.schedule_jpa.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

// Schedule 엔티티에 대해 데이터베이스 접근 기능을 제공하는 레포지토리 인터페이스
// JpaRepository를 상속받아 Schedule 엔티티와 그 기본 키 타입(Long)에 대한 기본적인 CRUD 및 페이징, 정렬 기능을 사용할 수 있음.
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
}