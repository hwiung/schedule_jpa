package com.example.schedule_jpa.services;

import com.example.schedule_jpa.entities.User;
import com.example.schedule_jpa.exceptions.CustomException;
import com.example.schedule_jpa.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    // 회원 가입
    public User signUp(User user) {
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new CustomException("유효하지 않은 요청입니다.");
        }
        return userRepository.save(user);
    }

    // 로그인
    public void login(String email, String password) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new CustomException("이메일 또는 비밀번호가 일치하지 않습니다."));

        if (!user.getPassword().equals(password)) {
            throw new CustomException("이메일 또는 비밀번호가 일치하지 않습니다.");
        }
    }
}
