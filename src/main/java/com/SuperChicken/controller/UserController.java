package com.SuperChicken.controller;

import com.SuperChicken.dto.RegisterDto;
import com.SuperChicken.dynamodb.User;
import com.SuperChicken.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/register")
    public boolean register(@RequestBody RegisterDto dto) {
        return userService.register(dto);
    }

    @GetMapping("/search/all")
    public List<User> findAll(@RequestParam int page) {
        return userService.findAll(PageRequest.of(page, 100));
    }

    @GetMapping("/search/nation-code/{nationCode}")
    public List<User> findByNationCode(@PathVariable String nationCode, @RequestParam int page) {
        return userService.findByNationCode(nationCode, PageRequest.of(page, 100));
    }
}
