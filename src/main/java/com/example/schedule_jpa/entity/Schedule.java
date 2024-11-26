package com.example.schedule_jpa.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
public class Schedule extends BaseEntity{       // BaseEntity 클래스를 상속받아 createdAt 및 modifiedAt 필드를 재사용하며,
                                                    // @CreatedDate 및 @LastModifiedDate로 생성 및 수정 시점이 자동 관리됨.


    @Id     // 기본 키(primary key)로 사용되는 필드를 지정(데이터베이스에서 유일하게 식별되는 값)
    @GeneratedValue(strategy = GenerationType.IDENTITY)     // 기본 키 값이 자동으로 생성되도록 설정(IDENTITY 전략은 데이터베이스의 AUTO_INCREMENT 기능을 사용하여 값을 생성)
    private Long id;
    private String title;
    private String content;

    @ManyToOne  // 연관관계 -> 다대일 관계(일정N to 유저1)
    @JoinColumn(name = "user_id")   // user 필드와 데이터베이스의 외래 키(foreign key) 컬럼을 매핑(외래 키 컬럼 이름: user_id)
    private User user;

    public Schedule(User user, String title, String content) {      // 일정 생성 시 User 객체, 제목, 내용을 전달받아 초기화.
        this.user = user;
        this.title = title;
        this.content = content;
    }

    public Schedule() {}        // JPA가 엔티티를 로딩하거나 프록시를 생성할 때 기본 생성자가 필요.

    public void update(String title, String content) {      // 일정의 제목과 내용을 업데이트하기 위한 메서드.
        this.title = title;
        this.content = content;
    }
}

