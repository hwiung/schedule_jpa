package com.example.schedule_jpa.dto;

import com.example.schedule_jpa.entity.User;
import lombok.Getter;

@Getter
public class UserRequestDto {       // 클라이언트로부터 사용자 생성 요청 데이터를 수신하기 위한 DTO 클래스.
    private final String userName;
    private final String email;
    private final String password;

    public UserRequestDto(String userName, String email, String password) {     // 객체를 생성하면서 필드(userName, email, password)를 초기화.
        this.userName =userName;
        this.email = email;
        this.password = password;
    }

    public User toEntity() {        // DTO 객체를 User 엔티티 객체로 변환하는 메서드.
        return new User(
                this.userName,
                this.email,
                this.password
        );
    }
}

