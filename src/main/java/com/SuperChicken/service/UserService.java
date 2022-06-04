package com.SuperChicken.service;

import com.SuperChicken.dto.RegisterDto;
import com.SuperChicken.dynamodb.User;
import com.SuperChicken.dynamodb.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public boolean register(RegisterDto dto) {
        try {
            userRepository.save(
                User.builder()
                    .email(dto.getEmail())
                    .name(dto.getName())
                    .phone(dto.getPhone())
                    .agreement(dto.isAgreement())
                    .build()
            );
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
