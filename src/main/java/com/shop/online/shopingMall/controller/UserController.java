package com.shop.online.shopingMall.controller;

import com.shop.online.shopingMall.domain.User;
import com.shop.online.shopingMall.dto.UserDto;
import com.shop.online.shopingMall.service.UserService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    /*
    * 회원 가입 관련 컨트롤러
    */
    @PostMapping("/user")
    public void signUp(@RequestBody @NonNull UserDto userDto) {
        User user = userDto.toEntity();
        userService.save(user);
    }

//    @GetMapping("/user/check")
//    public void duplicatedEmail(@RequestParam @NonNull )

}
