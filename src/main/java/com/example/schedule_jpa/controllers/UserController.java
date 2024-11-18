package com.example.schedule_jpa.controllers;

import com.example.schedule_jpa.dtos.user.UserRequestDTO;
import com.example.schedule_jpa.entities.User;
import com.example.schedule_jpa.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<?> signUp(@RequestBody @Valid UserRequestDTO requestDTO) {
        User user = userService.signUp(User.builder()
                .username(requestDTO.getUsername())
                .email(requestDTO.getEmail())
                .password(requestDTO.getPassword())
                .build());
        return ResponseEntity.ok(Map.of(
                "id", user.getId(),
                "username", user.getUsername(),
                "email", user.getEmail(),
                "createdData", user.getCreatedDate(),
                "modifiedDate", user.getModifiedDate()
        ));
    }
}
