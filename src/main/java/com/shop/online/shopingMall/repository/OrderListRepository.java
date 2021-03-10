package com.shop.online.shopingMall.repository;

import com.shop.online.shopingMall.domain.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderListRepository extends JpaRepository<OrderItem, Long> {
}
