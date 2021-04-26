package com.shop.online.shopingMall.repository;

import com.shop.online.shopingMall.domain.Cart;
import com.shop.online.shopingMall.domain.CartItem;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CartRepositoryTest {

    @Autowired
    private CartRepository cartRepository;

    @Test
    public void redisTest() {
        List<CartItem> carts = new ArrayList<>();
        CartItem numA = new CartItem("1", "RED", "1000", "95");
        carts.add(numA);
        Cart cart = new Cart("1", "TEST", carts);
        Cart save = cartRepository.save(cart);

        System.out.println(save.getId());

    }
}