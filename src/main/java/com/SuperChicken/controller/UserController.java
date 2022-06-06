package com.SuperChicken.controller;

import com.SuperChicken.dto.RegisterDto;
import com.SuperChicken.dynamodb.User;
import com.SuperChicken.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@ModelAttribute RegisterDto dto) {
        return userService.register(dto);
    }

    @GetMapping("/search/all")
    public Page<User> findAll(@RequestParam int page) {
        return userService.findAll(PageRequest.of(page, 100));
    }

    @GetMapping("/search/nation-code/{nationCode}")
    public Page<User> findByNationCode(@PathVariable String nationCode, @RequestParam int page) {
        return userService.findByNationCode(nationCode, PageRequest.of(page, 100));
    }
}
