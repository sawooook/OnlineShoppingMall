package com.shop.online.shopingMall.repository;

import com.shop.online.shopingMall.domain.Order;

import java.util.List;

public interface OrderRepositoryCustom {
    List<Order> findOrderList(Long userId);
}
