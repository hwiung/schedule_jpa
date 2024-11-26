package com.example.schedule_jpa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class ScheduleJpaApplication {

    public static void main(String[] args) {
        SpringApplication.run(ScheduleJpaApplication.class, args);
    }
}















// lv.1 일정 crud -> 저장, 조회, 수정, 삭제
    // api -> url -> url 정의 -> controller
    // service
    // repository -> db
    // entity
    // dto

// lv.2 유저 crud -> 유저 저장, 조회, 삭제
    // 유저명, 이메일, 작성일, 수정일
    // 작성, 수정일은 jpa auditing
// 연관관계 구현
    // 일정 테이블이 작성 유저명 대신 유저 고유 식별자를 가진다.

// lv.3 비밀번호 추가

// lv.4 로그인
    // cookie/session
    // filter, configuration annotation
    // 이메일, 비밀번호 사용하여 로그인
    // 회원가입, 로그인 요청은 인증 처리에서 제외
    // 이메일과 비밀번호가 일치하지 않을 경우 401 반환

    // 로그인 기능 구현 -> 이메일, 비밀번호가 일치 = 통과
        // 세션 설정
    // 필터 구현
    // 등록
    // 테스트

