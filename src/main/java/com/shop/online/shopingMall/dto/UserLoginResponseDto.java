package com.shop.online.shopingMall.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class UserLoginResponseDto {

    private Long id;
    private String name;
    private String phone;
    private String email;

    @Builder
    public UserLoginResponseDto(Long id, String name, String phone, String email, String addressCode, String addressDetail) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
    }
}
