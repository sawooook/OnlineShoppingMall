package com.shop.online.shopingMall.exception;

public class OrderCancelFail extends RuntimeException {
    public OrderCancelFail() {
        super();
    }

    public OrderCancelFail(String message) {
        super(message);
    }

    public OrderCancelFail(String message, Throwable cause) {
        super(message, cause);
    }

    public OrderCancelFail(Throwable cause) {
        super(cause);
    }

    protected OrderCancelFail(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
