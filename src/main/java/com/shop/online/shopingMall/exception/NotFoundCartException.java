package com.shop.online.shopingMall.exception;

public class NotFoundCartException extends RuntimeException {
    public NotFoundCartException() {
        super();
    }

    public NotFoundCartException(String message) {
        super(message);
    }

    public NotFoundCartException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotFoundCartException(Throwable cause) {
        super(cause);
    }

    protected NotFoundCartException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
