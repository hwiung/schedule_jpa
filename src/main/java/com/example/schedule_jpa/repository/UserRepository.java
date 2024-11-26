package com.example.schedule_jpa.repository;

import com.example.schedule_jpa.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;



// User 엔티티에 대해 데이터베이스 접근 기능을 제공하는 리포지토리 인터페이스
// JpaRepository를 상속받아 User 엔티티와 그 기본 키 타입(Long)에 대한 기본적인 CRUD 및 페이징, 정렬 기능을 사용할 수 있음
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}