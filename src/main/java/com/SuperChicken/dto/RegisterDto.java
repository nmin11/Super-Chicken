package com.SuperChicken.dto;

import lombok.Data;

@Data
public class RegisterDto {
    private String email;
    private String name;
    private String nationCode;
    private String phone;
    private boolean agreement;
}
