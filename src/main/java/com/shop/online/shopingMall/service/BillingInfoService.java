package com.shop.online.shopingMall.service;

import com.shop.online.shopingMall.domain.Order;
import com.shop.online.shopingMall.dto.order.OrderResultResponseDto;

public interface BillingInfoService {

    void ready();

    void approve(String token, Long id);

    OrderResultResponseDto charge(Order order);

}
