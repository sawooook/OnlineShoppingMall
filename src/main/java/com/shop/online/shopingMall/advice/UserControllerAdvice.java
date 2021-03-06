//package com.shop.online.shopingMall.advice;
//
//import com.shop.online.shopingMall.Exception.NotFoundUserException;
//import com.shop.online.shopingMall.concern.ResponseMessage;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.bind.annotation.RestControllerAdvice;
//
//@ControllerAdvice
//public class UserControllerAdvice {
//
//    @ExceptionHandler(NotFoundUserException.class)
//    public ResponseEntity errorHandler(Exception e) {
//
//        return ResponseEntity.badRequest().body(new ResponseMessage(e.getMessage()));
//    }
//}
