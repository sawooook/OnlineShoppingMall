package com.shop.online.shopingMall.dto.user;

import com.shop.online.shopingMall.domain.User;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserLoginResponseDto {
    private Long id;
    private String name;
    private String jwtToken;
    private String phone;
    private String email;
    private AddressDto address;

    public UserLoginResponseDto(User user, String token, AddressDto addressDto) {
        this.id = user.getId();
        this.name = user.getName();
        this.jwtToken = token;
        this.phone = user.getPhone();
        this.email = user.getEmail();
        this.address = addressDto;
    }


}
