package com.shop.online.shopingMall.repository;

import com.shop.online.shopingMall.domain.*;
import com.shop.online.shopingMall.domain.enumType.ProductOptionStatus;
import com.shop.online.shopingMall.domain.enumType.ProductStatus;
import com.shop.online.shopingMall.domain.enumType.UserRole;
import com.shop.online.shopingMall.domain.enumType.UserStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class OrderRepositoryImplTest {

    @Autowired
    private OrderRepository orderRepository;



    @Test
    void findOrderList() {
        orderRepository.findOrderList(1L);
    }


}