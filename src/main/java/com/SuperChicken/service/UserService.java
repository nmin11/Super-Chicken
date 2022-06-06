package com.SuperChicken.service;

import com.SuperChicken.dto.RegisterDto;
import com.SuperChicken.dynamodb.User;
import com.SuperChicken.dynamodb.UserRepository;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final Logger logger = LogManager.getLogger();

    public boolean register(RegisterDto dto) {
        try {
            userRepository.save(
                User.builder()
                    .email(dto.getEmail())
                    .name(dto.getName())
                    .phone(dto.getNationCode() + " " + dto.getPhone())
                    .agreement(dto.isAgreement())
                    .build()
            );
            return true;
        } catch (Exception e) {
            return false;
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
