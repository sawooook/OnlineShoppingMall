package com.shop.online.shopingMall.service;

import com.shop.online.shopingMall.domain.Order;
import com.shop.online.shopingMall.dto.order.OrderResultResponseDto;

public class NaverPayServiceImpl implements BillingInfoService {
    @Override
    public void ready() {

    }

    @Override
    public void approve(String token, Long id) {

    }

    @Override
    public OrderResultResponseDto charge(Order order) {
        return null;
    }
}
