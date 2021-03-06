package com.shop.online.shopingMall.controller;

import com.shop.online.shopingMall.concern.ResponseMessage;
import com.shop.online.shopingMall.concern.ResponseStatus;
import com.shop.online.shopingMall.domain.User;
import com.shop.online.shopingMall.dto.user.UserDto;
import com.shop.online.shopingMall.dto.user.UserLoginRequestDto;
import com.shop.online.shopingMall.dto.user.UserLoginResponseDto;
import com.shop.online.shopingMall.service.UserService;
import javassist.NotFoundException;
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
    * 회원가입에 성공시 Status 201과, user 객체를 내려준다.
    */
    @PostMapping("/singUp")
    public ResponseEntity signUp(@RequestBody @NonNull UserDto userDto) {
        userService.save(userDto);
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

    /*
    * 로그인 관련 API
    *
    * 성공시 return OK, user 정보
    * 실패시 return BadRequest
    *
    * */
    @PostMapping("/login")
    public ResponseEntity Login(@RequestBody @NonNull UserLoginRequestDto userLoginRequestDto) {
        String email = userLoginRequestDto.getEmail();
        String passWord = userLoginRequestDto.getPassWord();
        UserLoginResponseDto userLoginResponseDto = userService.loginCheck(email, passWord);

        return ResponseEntity.ok().body(new ResponseMessage(ResponseStatus.OK,"로그인 성공", userLoginResponseDto));
    }

    @GetMapping("/delete/{id}")
    public ResponseEntity unRegister(@NonNull Long id) throws NotFoundException {
        userService.unRegister(id);
        return ResponseEntity.ok().body(new ResponseMessage(ResponseStatus.OK,"회원탈퇴 완료", null));
    }
}
