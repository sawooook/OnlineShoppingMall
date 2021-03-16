package com.shop.online.shopingMall.dto.user;

import com.shop.online.shopingMall.domain.User;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserLoginResponseDto {
    private Long id;
    private String name;
    private String jwtToken;
    private String phone;
    private String email;
    private AddressDto address;

//    public static UserLoginResponseDto toEntity(User user) {
//        var addressDto = com.shop.online.shopingMall.dto.user.AddressDto.toEntity(address);
//        return UserLoginResponseDto.builder()
//                .id(user.getId()).name(user.getName()).email(user.getEmail()).phone(user.getPhone()).build();
//    }

    public static UserLoginResponseDto toDto(User user) {
        var addressDto = com.shop.online.shopingMall.dto.user.AddressDto.toDto(user.getAddress());

        return UserLoginResponseDto.builder().address(addressDto)
                .id(user.getId()).name(user.getName()).email(user.getEmail()).phone(user.getPhone()).build();
    }

    public void updateToken(String token) {
        setJwtToken(token);
    }
}
