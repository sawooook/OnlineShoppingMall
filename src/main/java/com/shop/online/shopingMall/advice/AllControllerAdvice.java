package com.shop.online.shopingMall.advice;

import com.shop.online.shopingMall.exception.NotFoundCartException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.shop.online.shopingMall.util.ApiResponse;

import static com.shop.online.shopingMall.util.ApiResponse.*;

/**
*  ControllerAdvice -> 컨트롤러 전역에서 발생할 수 있는 예외를 잡아주는 어노테이션
* */

@ControllerAdvice
public class AllControllerAdvice {

    @ExceptionHandler(NotFoundCartException.class)
    public ApiResponse<String> notFoundCartException(Exception e) {
        return success(e.getMessage());
    }
}
