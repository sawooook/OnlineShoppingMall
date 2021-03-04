package com.shop.online.shopingMall.controller;

import com.shop.online.shopingMall.domain.User;
import com.shop.online.shopingMall.dto.UserDto;
import com.shop.online.shopingMall.dto.UserLoginRequestDto;
import com.shop.online.shopingMall.dto.UserLoginResponseDto;
import com.shop.online.shopingMall.service.UserService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

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
        return ResponseEntity.ok().build();
    }

    /*
    * 이메일 중복 체크 API
    *
    * 이메일 중복시
    * STATUS : notFound
    *
    * 중복이 아닐시
    * STATUS : OK
    * */
    @GetMapping("/check/{email}")
    public ResponseEntity checkEmail(@PathVariable @NonNull String email) {
        Boolean checkEmailStatus = userService.checkEmail(email);

        if (checkEmailStatus) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok().build();
        }
    }

    @PostMapping("/login")
    public ResponseEntity Login(@RequestBody @NonNull UserLoginRequestDto userLoginRequestDto) {
        String email = userLoginRequestDto.getEmail();
        String passWord = userLoginRequestDto.getPassWord();

        Optional<UserLoginResponseDto> userResponseDto = userService.loginCheck(email, passWord);

        if (userResponseDto.isPresent()) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.badRequest().body("가입하지 않은 아이디입니다.");
        }
    }
}
