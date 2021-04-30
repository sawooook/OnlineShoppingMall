package com.shop.online.shopingMall.exception;

import java.util.function.Supplier;

public class NotActiveBillingInfoExcption extends RuntimeException {
    public NotActiveBillingInfoExcption(String message) {
        super(message);
    }
}
