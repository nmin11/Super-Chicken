package com.SuperChicken.service;

import com.SuperChicken.dto.RegisterDto;
import com.SuperChicken.dynamodb.User;
import com.SuperChicken.dynamodb.UserRepository;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final Logger logger = LogManager.getLogger();

    public ResponseEntity<?> register(RegisterDto dto) {
        if (dto.getName().equals("") || dto.getEmail().equals("") || dto.getPhone().equals("")) {
            return ResponseEntity.status(400).body("Submit Fail: Please fill out the entire form");
        }

        if (userRepository.findByEmail(dto.getEmail()).isPresent()) {
            return ResponseEntity.status(400).body("Submit Fail: Duplicated Email");
        }

        try {
            userRepository.save(
                User.builder()
                    .email(dto.getEmail())
                    .name(dto.getName())
                    .phone(dto.getNationCode() + " " + dto.getPhone())
                    .agreement(dto.isAgreement())
                    .build()
            );
            return ResponseEntity.status(200).body("Submit Success!");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Submit Fail: Database Connection Error");
        }
    }

    public Page<User> findAll(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    public Page<User> findByNationCode(String nationCode, Pageable pageable) {
        logger.info("nation code = " + nationCode);
        return userRepository.findAllByPhoneStartingWith(nationCode, pageable);
    }
}
