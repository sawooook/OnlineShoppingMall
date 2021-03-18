package com.shop.online.shopingMall.exception;

public class NotFoundDeliveryException extends RuntimeException {
    public NotFoundDeliveryException() {
    }

    public NotFoundDeliveryException(String message) {
        super(message);
    }

    public NotFoundDeliveryException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotFoundDeliveryException(Throwable cause) {
        super(cause);
    }

    public NotFoundDeliveryException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
