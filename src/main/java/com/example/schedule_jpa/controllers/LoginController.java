package com.example.schedule_jpa.controllers;

import com.example.schedule_jpa.dtos.user.LoginRequestDTO;
import com.example.schedule_jpa.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/login")
@RequiredArgsConstructor
public class LoginController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<?> login(@RequestBody @Valid LoginRequestDTO requestDTO) {
        userService.login(requestDTO.getEmail(), requestDTO.getPassword());
        return ResponseEntity.ok(Map.of("message", "로그인 성공"));
    }
}
