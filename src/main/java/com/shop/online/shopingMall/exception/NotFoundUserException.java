package com.shop.online.shopingMall.exception;

public class NotFoundUserException extends RuntimeException {

    public NotFoundUserException() {
        super("로그인 실패");
    }
}
