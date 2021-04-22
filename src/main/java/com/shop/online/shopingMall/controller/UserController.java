package com.shop.online.shopingMall.controller;

import com.shop.online.shopingMall.aop.LoginUserCheck;
import com.shop.online.shopingMall.concern.ResponseMessage;
import com.shop.online.shopingMall.concern.ResponseStatus;
import com.shop.online.shopingMall.domain.User;
import com.shop.online.shopingMall.dto.user.*;
import com.shop.online.shopingMall.exception.NotFoundUserException;
import com.shop.online.shopingMall.service.UserService;
import javassist.NotFoundException;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {




    private final UserService userService;

    /**
    * 회원 가입 API
    *
    * 회원가입 성공시
    * Status : OK
    * user 정보
    */
    @PostMapping("/signUp")
    public ResponseEntity signUp(@RequestBody @NonNull UserDto userDto) {
        User user = userService.save(userDto);
        return ResponseEntity.ok().body(new ResponseMessage(ResponseStatus.OK ,new UserDto(user)));
    }

    /**
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
        boolean checkEmailStatus = userService.checkEmail(email);
        if (checkEmailStatus) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok().build();
        }
    }

    /**
    * 로그인 관련 API
    *
    * 성공시 return OK, user 정보
    * 실패시 return BadRequest
    *
    * */
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @NonNull UserLoginRequestDto userLoginRequestDto) {
        String email = userLoginRequestDto.getEmail();
        String passWord = userLoginRequestDto.getPassWord();
        UserLoginResponseDto userLoginResponseDto = userService.loginCheck(email, passWord);

        return ResponseEntity.ok().body(new ResponseMessage(ResponseStatus.OK, userLoginResponseDto));
    }

    @GetMapping("/delete/{id}")
    public ResponseEntity unRegister(@NonNull Long id) throws NotFoundUserException {
        userService.unRegister(id);
        return ResponseEntity.ok().body(new ResponseMessage(ResponseStatus.OK, null));
    }

    /**
     * 마이페이지 관련 API
     *
     * 성공시 return OK, user 정보
     * 실패시 return BadRequest
     *
     * */
    @GetMapping("/mypage/{id}")
    public ResponseEntity myPage(@PathVariable @NonNull Long id) throws NotFoundUserException {
        User user = userService.findUser(id);
        UserResponseDto response = new UserResponseDto(user, new AddressDto(user));
        return ResponseEntity.ok().body(new ResponseMessage(ResponseStatus.OK, response));
    }
}
