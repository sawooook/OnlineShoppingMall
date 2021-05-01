package com.shop.online.shopingMall.dto.user;

import com.shop.online.shopingMall.domain.Address;
import com.shop.online.shopingMall.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class UserResponseDto {
    private Long id;
    private String email;
    private String name;
    private String phone;
    private AddressDto address;

    public UserResponseDto(User user, AddressDto addressDto) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.name = user.getName();
        this.phone = user.getPhone();
        this.address = addressDto;
    }
}
