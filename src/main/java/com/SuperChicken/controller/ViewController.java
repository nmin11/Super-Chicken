package com.SuperChicken.controller;

import com.SuperChicken.dto.RegisterDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class ViewController {
    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("registerDto", new RegisterDto());
        return "/index";
    }
}
