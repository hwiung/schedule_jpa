package com.example.schedule_jpa.entity;

import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@MappedSuperclass   // JPA 어노테이션이며 해당 클래스를 다른 엔티티 클래스에서 상속받아 사용할 수 있도록 설정해줌.
@EntityListeners(AuditingEntityListener.class)  // Auditing 기능을 활성화하기 위함(@CreatedDate와 @LastModifiedDate를 자동으로 처리하기 위해 Auditing 기능을 사용)
public abstract class BaseEntity {  // 하위 클래스에서 상속받아 사용되며 모든 엔티티에 공통적으로 필요한 생성 시간 및 수정 시간 필드를 제공함.

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime modifiedAt;
}

