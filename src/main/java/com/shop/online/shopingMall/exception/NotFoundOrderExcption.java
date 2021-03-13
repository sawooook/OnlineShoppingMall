package com.shop.online.shopingMall.exception;

public class NotFoundOrderExcption extends RuntimeException{
    public NotFoundOrderExcption() {
        super();
    }

    public NotFoundOrderExcption(String message) {
        super(message);
    }

    public NotFoundOrderExcption(String message, Throwable cause) {
        super(message, cause);
    }

    public NotFoundOrderExcption(Throwable cause) {
        super(cause);
    }

    protected NotFoundOrderExcption(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
