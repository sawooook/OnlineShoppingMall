package com.shop.online.shopingMall.dto;

import lombok.Data;

@Data
public class KakaoPayReadyResponseDto {
    private String tid;
    private String next_redirect_app_url;
    private String next_redirect_mobile_url;
    private String android_app_scheme;
    private String ios_app_scheme;
    private String created_at;

}
