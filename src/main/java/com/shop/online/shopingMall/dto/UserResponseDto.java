package com.shop.online.shopingMall.dto;

import com.shop.online.shopingMall.domain.Address;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserResponseDto {
    private Long id;
    private String email;
    private String name;
    private String phone;
    private Address address;

}
