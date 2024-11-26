package com.example.schedule_jpa.controller;

import com.example.schedule_jpa.dto.LoginRequestDto;
import com.example.schedule_jpa.dto.UserRequestDto;
import com.example.schedule_jpa.dto.UserResponseDto;
import com.example.schedule_jpa.entity.User;
import com.example.schedule_jpa.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")   // 컨트롤러 내의 모든 API 요청 경로 앞에 /users를 기본으로 추가해줌.
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;  // UserService를 주입받아 사용자 관련 로직을 처리함.

    @PostMapping    // POST 요청을 처리.
    public ResponseEntity<UserResponseDto> createUser(@RequestBody UserRequestDto userRequestDto) {
                                                        // 클라이언트가 요청한 JSON 데이터를 UserRequestDto 객체로 매핑.
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(userRequestDto));
    }           // HTTP 상태 코드와 데이터를 함께 반환.

    @GetMapping // GET 요청을 처리.
    public ResponseEntity<List<UserResponseDto>> findAll() {
        return ResponseEntity.ok().body(userService.findAll());
    }
                                                                                            // 서비스에서 모든 사용자를 조회하고 리스트로 반환.
    @DeleteMapping("/{id}") // DELETE 요청을 처리.
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {   // URL 경로에 포함된 id 값을 매핑.
        userService.deleteUser(id);  // 주어진 ID에 해당하는 사용자를 삭제.
        return ResponseEntity.ok().body("삭제됐습니다.");
    }

    @PostMapping("/login")  // POST 요청을 처리, /users/login 경로에 매핑                     // HTTP 요청 정보를 받아 세션을 관리하기 위해 사용.
    public ResponseEntity<String> loginUser(@RequestBody LoginRequestDto loginRequestDto, HttpServletRequest request) {
                                                // 요청 JSON 데이터를 LoginRequestDto로 매핑.
        User loginedUser = userService.loginUser(loginRequestDto);  // 로그인 검증 로직 수행.
        HttpSession session = request.getSession(); // 세션을 가져오거나, 없으면 새로 생성.
        session.setAttribute("SESSION KEY", loginedUser.getId());   // 로그인 구현 -> 세션 쿠키 방식

        return ResponseEntity.ok().body("로그인됐습니다.");
    }

    @PostMapping("/logout")     // POST 요청을 처리, /users/logout 경로에 매핑.
    public ResponseEntity<String> logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);    // 현재 세션이 존재하면 가져오고, 없으면 null 반환.
        if(session != null) {
            session.invalidate(); // 로그인 무효화
        }
        return ResponseEntity.ok("로그아웃 성공");
    }
}

