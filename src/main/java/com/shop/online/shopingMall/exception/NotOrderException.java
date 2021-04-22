package com.shop.online.shopingMall.exception;

public class NotOrderException extends RuntimeException{
    public NotOrderException() {
        super("주문을 할 수 없습니다.");
    }
}
