package com.shop.online.shopingMall.dto;

import com.shop.online.shopingMall.domain.Address;
import com.shop.online.shopingMall.domain.User;
import com.shop.online.shopingMall.domain.enumType.UserRole;
import com.shop.online.shopingMall.domain.enumType.UserStatus;
import lombok.Data;
import lombok.NonNull;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDateTime;

/*
* 사용자관련 DTO
*
* */


@Data
public class UserDto {

    @NonNull
    private String id;

    @NonNull
    private String passWord;

    @NonNull
    private String name;

    @NonNull
    private String phone;

    @NonNull
    private String email;
    // 생성날짜

    @NonNull
    private LocalDateTime createdAt;
    // 마지막 업데이트 날짜

    @NonNull
    private LocalDateTime updatedAt;

    @NonNull
    private String addressCode;

    @NonNull
    private String addressDetail;

    @NonNull
    private UserRole userRole;

    @NonNull
    @ColumnDefault(value = "SIGN")
    private UserStatus userStatus;

    public User toEntity() {
        Address adrress = Address.builder().addressCode(getAddressCode())
                .addressDetail(getAddressDetail()).build();

        return User.builder().password(this.passWord)
                .email(this.email)
                .phone(this.phone).address(adrress)
                .name(this.name).userRole(this.userRole).userStatus(this.userStatus).build();
    }
}
