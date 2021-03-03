package com.shop.online.shopingMall.controller;

import com.shop.online.shopingMall.domain.User;
import com.shop.online.shopingMall.dto.ResponseMessage;
import com.shop.online.shopingMall.dto.ResponseStatus;
import com.shop.online.shopingMall.dto.UserDto;
import com.shop.online.shopingMall.service.UserService;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.aspectj.bridge.Message;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.Charset;

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
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        ResponseMessage message = ResponseMessage.builder().message("SUCCESS").responseStatus(ResponseStatus.OK).data(user).build();
        return new ResponseEntity<>(message, headers, HttpStatus.OK);
    }

//    @GetMapping("/user/check")
//    public void duplicatedEmail(@RequestParam @NonNull )

    @GetMapping("/test")
    public ResponseEntity Test() {
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/check/{email}")
    public ResponseEntity checkEmail(@PathVariable @NonNull String email) {
        System.out.println(email);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
