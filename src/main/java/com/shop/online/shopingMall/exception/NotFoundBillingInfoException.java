package com.shop.online.shopingMall.exception;

public class NotFoundBillingInfoException extends RuntimeException{
    public NotFoundBillingInfoException() {
        super();
    }

    public NotFoundBillingInfoException(String message) {
        super(message);
    }

    public NotFoundBillingInfoException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotFoundBillingInfoException(Throwable cause) {
        super(cause);
    }

    protected NotFoundBillingInfoException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
