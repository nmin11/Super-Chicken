package com.SuperChicken.controller;

import com.SuperChicken.dto.NationCodeDto;
import com.SuperChicken.dto.RegisterDto;
import com.SuperChicken.dynamodb.User;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequiredArgsConstructor
public class ViewController {
    private final Logger logger = LogManager.getLogger();
    private final UserController userController;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("registerDto", new RegisterDto());
        return "index";
    }

    @PostMapping("/submit")
    public ModelAndView submit(@ModelAttribute RegisterDto dto, ModelAndView mav) {
        ResponseEntity<?> result = userController.register(dto);
        logger.info("submit result = " + result.getBody());
        mav.addObject("submitResult", result.getBody());
        mav.setViewName("submit-result");
        return mav;
    }

    @GetMapping("/admin")
    public String admin(Model model) {
        model.addAttribute("nationCodeDto", new NationCodeDto());
        return "admin";
    }

    @GetMapping("/search-all")
    public String searchAll(@RequestParam int page, Model model) {
        Page<User> submitters = userController.findAll(page - 1);
        int totalPages = submitters.getTotalPages();
        logger.info("total pages of search all = " + totalPages);
        model.addAttribute("submitters", submitters);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("searchingCondition", "all");
        return "submitter-list";
    }

    @GetMapping("/search-nation-code")
    public String searchByNationCode(@RequestParam String nationCode, @RequestParam int page, Model model) {
        Page<User> submitters = userController.findByNationCode(nationCode, page - 1);
        int totalPages = submitters.getTotalPages();
        logger.info("total pages of search by nation code = " + totalPages);
        model.addAttribute("submitters", submitters);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("searchingCondition", "nationCode");
        model.addAttribute("nationCode", nationCode);
        return "submitter-list";
    }
}
