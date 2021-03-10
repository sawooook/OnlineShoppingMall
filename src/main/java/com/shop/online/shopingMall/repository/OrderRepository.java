package com.shop.online.shopingMall.repository;

import com.shop.online.shopingMall.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
