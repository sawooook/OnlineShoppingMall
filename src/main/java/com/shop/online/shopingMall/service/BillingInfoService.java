package com.shop.online.shopingMall.service;

import com.shop.online.shopingMall.domain.Order;
import com.shop.online.shopingMall.dto.util.KakaoPayChargeResponseDto;

public interface BillingInfoService {

    void ready();

    void approve(String token, Long id);

    void charge(Order order);

}
