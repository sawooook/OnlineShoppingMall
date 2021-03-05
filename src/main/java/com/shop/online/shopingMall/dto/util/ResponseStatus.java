package com.shop.online.shopingMall.dto.util;

public enum ResponseStatus {
    OK(200, "OK"),
    BAD_REQUEST(400, "BAD_REQUEST"),
    CREATED(201, "CREATED");

    int statusCode;
    String code;

    ResponseStatus(int statusCode, String code) {
        this.statusCode = statusCode;
        this.code = code;
    }
}
