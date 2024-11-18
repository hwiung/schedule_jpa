package com.example.schedule_jpa.exceptions;

public class CustomException extends RuntimeException {

    public CustomException(String message) {
        super(message); // RuntimeException의 생성자 호출
    }
}
