package com.example.schedule_jpa.service;

import com.example.schedule_jpa.dto.LoginRequestDto;
import com.example.schedule_jpa.dto.UserRequestDto;
import com.example.schedule_jpa.dto.UserResponseDto;
import com.example.schedule_jpa.entity.User;
import com.example.schedule_jpa.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;    // // 데이터베이스와 통신하기 위한 UserRepository 객체

    @Transactional
    public UserResponseDto createUser(UserRequestDto userRequestDto) {
        // 요청받은 UserRequestDto를 User 엔티티로 변환하고, 데이터베이스에 저장
        User savedUser = userRepository.save(userRequestDto.toEntity());

        // 저장된 엔티티를 UserResponseDto로 변환하여 반환
        return UserResponseDto.toDto(savedUser);
    }

    // 특정 ID의 유저를 검색하는 메서드
    public User findUserById(Long id) {
            return userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("유저를 찾을 수 없습니다."));
    }   // 데이터베이스에서 해당 ID의 유저를 검색, 없을 경우 IllegalArgumentException 예외를 발생시킴.

    // 모든 유저 조회 메서드
    public List<UserResponseDto> findAll() {
        List<User> users = userRepository.findAll();
        // 데이터베이스에서 모든 유저를 검색
        // 엔티티를 DTO로 변환하여 반환(List<user> -> List<userResponseDto>)
        return users
                .stream()   // Stream API를 사용하여 리스트 처리
                .map(UserResponseDto::toDto)    // User 객체를 UserResponseDto로 변환
                .toList();  // 변환된 결과를 리스트로 수집
    }

    @Transactional
    public void deleteUser(Long id) {       // 유저 삭제 메서드
        findUserById(id);           // 삭제하려는 유저가 존재하는지 확인
        userRepository.deleteById(id);          // 데이터베이스에서 해당 ID의 유저 삭제
    }

    // 유저 로그인 처리 메서드
    public User loginUser(LoginRequestDto loginRequestDto) {
        // 입력받은 이메일로 유저를 검색
        User user = userRepository.findByEmail(loginRequestDto.getEmail());
        // 유저가 존재하지 않거나, 비밀번호가 일치하지 않을 경우 예외 발생
        if (user == null || !Objects.equals(user.getPassword(), loginRequestDto.getPassword())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "유효하지 않은 사용자 이름 또는 잘못된 비밀번호");
        }
        return user;    // 로그인 성공 시 유저 객체 반환
    }
}

