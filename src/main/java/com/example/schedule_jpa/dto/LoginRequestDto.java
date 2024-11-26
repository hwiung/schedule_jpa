package com.example.schedule_jpa.dto;

import lombok.Getter;

@Getter
public class LoginRequestDto {          // 사용자 로그인 요청을 처리하기 위해 사용되는 DTO 클래스
    private final String email;         // 클라이언트가 요청한 로그인 이메일을 저장.
    private final String password;      // 클라이언트가 요청한 로그인 비밀번호를 저장.

    public LoginRequestDto(String email, String password) {     // 로그인 요청 객체를 생성할 때, 이메일과 비밀번호를 초기화.
        this.email = email;                                     // 매개변수 email 값을 현재 객체의 email 필드에 할당.
        this.password = password;                               // 생성자 매개변수 password 값을 현재 객체의 password 필드에 할당.
    }
}

