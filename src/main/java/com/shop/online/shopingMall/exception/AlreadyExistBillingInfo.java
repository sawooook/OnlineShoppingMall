package com.shop.online.shopingMall.exception;

public class AlreadyExistBillingInfo extends RuntimeException{
    public AlreadyExistBillingInfo() {
    }

    public AlreadyExistBillingInfo(String message) {
        super(message);
    }

    public AlreadyExistBillingInfo(String message, Throwable cause) {
        super(message, cause);
    }

    public AlreadyExistBillingInfo(Throwable cause) {
        super(cause);
    }

    public AlreadyExistBillingInfo(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
