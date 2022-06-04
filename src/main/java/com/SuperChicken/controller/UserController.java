package com.SuperChicken.controller;

import com.SuperChicken.dto.RegisterDto;
import com.SuperChicken.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/register")
    public boolean register(@RequestBody RegisterDto dto) {
        return userService.register(dto);
    }
}
