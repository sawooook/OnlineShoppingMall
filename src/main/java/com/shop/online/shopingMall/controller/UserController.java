package com.shop.online.shopingMall.controller;

import com.shop.online.shopingMall.domain.User;
import com.shop.online.shopingMall.dto.user.*;
import com.shop.online.shopingMall.exception.NotFoundUserException;
import com.shop.online.shopingMall.service.UserService;
import com.shop.online.shopingMall.util.ApiResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import static com.shop.online.shopingMall.util.ApiResponse.success;

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
     * @return
     */
    @PostMapping("/signUp")
    public ApiResponse<UserResponseDto> signUp(@RequestBody UserDto userDto) {
        User user = userService.save(userDto);
        return success(new UserResponseDto(user));
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
    public ApiResponse checkEmail(@PathVariable @NonNull String email) {
        boolean checkEmailStatus = userService.checkEmail(email);
        if (checkEmailStatus) {
            return success(checkEmailStatus);
        } else {
            return success(false);
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
    public ApiResponse login(@RequestBody @NonNull UserLoginRequestDto userLoginRequestDto) {
        String email = userLoginRequestDto.getEmail();
        String passWord = userLoginRequestDto.getPassWord();
        UserLoginResponseDto userLoginResponseDto = userService.loginCheck(email, passWord);
        return success(userLoginResponseDto);
    }


    @GetMapping("/delete/{id}")
    public ApiResponse<Boolean> unRegister(@NonNull Long id) throws NotFoundUserException {
        userService.unRegister(id);
        return success(true);
    }

    /**
     * 마이페이지 관련 API
     *
     * 성공시 return OK, user 정보
     * 실패시 return BadRequest
     *
     * */
    @GetMapping("/mypage/{id}")
    public ApiResponse myPage(@PathVariable @NonNull Long id) throws NotFoundUserException {
        User user = userService.findUser(id);
        UserResponseDto response = new UserResponseDto(user);
        return success(response);
    }
}
