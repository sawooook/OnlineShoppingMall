package com.shop.online.shopingMall.dto;

import com.shop.online.shopingMall.domain.Address;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserLoginResponseDto {
    private Long id;
    private String name;
    private String phone;
    private String email;
    private Address address;
}
