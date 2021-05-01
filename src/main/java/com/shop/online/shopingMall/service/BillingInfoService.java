package com.shop.online.shopingMall.service;

import com.shop.online.shopingMall.domain.BillingInfo;
import com.shop.online.shopingMall.domain.Order;
import com.shop.online.shopingMall.domain.User;
import com.shop.online.shopingMall.dto.order.OrderResultResponseDto;

import java.util.Optional;

public interface BillingInfoService {

    void ready(Long userId);

    void approve(String token, Long id);

    OrderResultResponseDto charge(Order order);
}
