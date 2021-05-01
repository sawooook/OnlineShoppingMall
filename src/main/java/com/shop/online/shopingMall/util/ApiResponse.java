package com.shop.online.shopingMall.util;

public class ApiResponse<T> {

    private final T data;
    private final String error;

    public ApiResponse(T data, String error) {
        this.data = data;
        this.error = error;
    }

    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<T>(data, null);
    }

    public static <T> ApiResponse<T> fail(String message) {
        return new ApiResponse<T>(null, message);
    }

    public T getData() {
        return data;
    }

    public String getError() {
        return error;
    }
}