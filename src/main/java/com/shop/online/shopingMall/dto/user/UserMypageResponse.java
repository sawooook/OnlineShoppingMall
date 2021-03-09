package com.shop.online.shopingMall.dto.user;

import com.shop.online.shopingMall.domain.Address;
import com.shop.online.shopingMall.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @Builder
@NoArgsConstructor @AllArgsConstructor
public class UserMypageResponse {
    private Long id;
    private String email;
    private String name;
    private String phone;
    private AddressDto address;
    private String role;


    public static UserMypageResponse toDto(User user) {
        AddressDto addressDto = AddressDto.toDto(user.getAddress());

        return UserMypageResponse.builder()
                .id(user.getId()).name(user.getName())
                .phone(user.getPhone()).email(user.getEmail()).role(String.valueOf(user.getUserRole()))
                .address(addressDto).build();
    }
}
