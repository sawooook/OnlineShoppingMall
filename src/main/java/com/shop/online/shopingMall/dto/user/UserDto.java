package com.shop.online.shopingMall.dto.user;

import com.shop.online.shopingMall.domain.Address;
import com.shop.online.shopingMall.domain.User;
import com.shop.online.shopingMall.domain.enumType.UserRole;
import com.shop.online.shopingMall.domain.enumType.UserStatus;
import com.shop.online.shopingMall.service.UserService;
import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

/*
* 사용자관련 DTO
*
* */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    @NonNull
    private String passWord;

    @NonNull
    private String name;

    @NonNull
    private String phone;

    @NonNull
    private String email;
    // 생성날짜

    private AddressDto address;

    @NonNull
    private UserRole userRole;

    @ColumnDefault("SIGN")
    private UserStatus userStatus;

    public UserDto(User user) {
        this.name = user.getName();
        this.phone = user.getPhone();
        this.email = user.getEmail();
        this.address = new AddressDto(user);
        this.userRole = user.getUserRole();
    }
}
