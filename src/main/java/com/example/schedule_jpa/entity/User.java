package com.example.schedule_jpa.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class User extends BaseEntity {
    
    @Id     // 기본 키(primary key)로 사용되는 필드를 지정(데이터베이스에서 유일하게 식별되는 값)
    @GeneratedValue(strategy = GenerationType.IDENTITY)         // 기본 키 값이 자동으로 생성되도록 설정(IDENTITY 전략은 데이터베이스의 AUTO_INCREMENT 기능을 사용하여 값을 생성)
    private Long id;
    private String userName;
    private String email;
    private String password;

    public User() {}        // JPA가 엔티티를 로딩하거나 프록시를 생성할 때 기본 생성자가 필요.

    public User(String userName, String email, String password) {       // 새로운 사용자 객체를 생성할 때 이름, 이메일, 비밀번호를 초기화.
        this.userName = userName;
        this.email = email;
        this.password = password;
    }
}