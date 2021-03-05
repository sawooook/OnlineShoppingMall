package com.shop.online.shopingMall.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginRequestDto {

    @NonNull
    private String email;

    @NonNull
    private String passWord;
}
