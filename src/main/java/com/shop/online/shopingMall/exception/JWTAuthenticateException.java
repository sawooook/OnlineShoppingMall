package com.shop.online.shopingMall.exception;

public class JWTAuthenticateException extends RuntimeException {

    public JWTAuthenticateException() {
        super();
    }

    public JWTAuthenticateException(String message) {
        super(message);
    }

    public JWTAuthenticateException(String message, Throwable cause) {
        super(message, cause);
    }

    public JWTAuthenticateException(Throwable cause) {
        super(cause);
    }

    protected JWTAuthenticateException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
