package com.shop.online.shopingMall.controller;

import com.shop.online.shopingMall.domain.User;
import com.shop.online.shopingMall.dto.UserDto;
import com.shop.online.shopingMall.service.UserService;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    /*
    * 회원 가입 관련 컨트롤러
    * 회원가입에 성공시 Status 201과, User 객체를 내려준다.
    */
    @PostMapping("/singUp")
    public ResponseEntity signUp(@RequestBody @NonNull UserDto userDto) {
        User user = userDto.toEntity();
        userService.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

//    @GetMapping("/user/check")
//    public void duplicatedEmail(@RequestParam @NonNull )

    @GetMapping("/test")
    public ResponseEntity Test() {
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
