package com.shop.online.shopingMall.advice;

import com.shop.online.shopingMall.concern.ResponseMessage;
import com.shop.online.shopingMall.concern.ResponseStatus;
import com.shop.online.shopingMall.exception.NotFoundCartException;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.logging.Logger;

/*
*  ControllerAdvice -> 컨트롤러 전역에서 발생할 수 있는 예외를 잡아주는 어노테이션
*
* */

@ControllerAdvice
public class AllControllerAdvice {

    @ExceptionHandler(NotFoundCartException.class)
    public ResponseEntity notFoundCartException(Exception e) {
        return ResponseEntity.badRequest().body(new ResponseMessage(ResponseStatus.BAD_REQUEST,e.getMessage(), null));
    }
}
