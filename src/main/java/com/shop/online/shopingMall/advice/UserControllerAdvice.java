//package com.shop.online.shopingMall.advice;
//
//import com.shop.online.shopingMall.service.UserService;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.java.Log;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.bind.annotation.RestControllerAdvice;
//
//import javax.servlet.http.HttpServletRequest;
//import java.net.http.HttpHeaders;
//import java.util.logging.Logger;
//
//@RestControllerAdvice(annotations = RestController.class)
//public class UserControllerAdvice {
//
//    @ExceptionHandler
//    public ResponseEntity errorHandler(Exception e) {
//        return ResponseEntity.badRequest().body("로그인에 실패 하였습니다");
//    }
//}
