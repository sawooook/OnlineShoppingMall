package com.shop.online.shopingMall.repository;

import com.shop.online.shopingMall.domain.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
