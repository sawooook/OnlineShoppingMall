package com.shop.online.shopingMall.service;

import com.shop.online.shopingMall.domain.Cart;
import com.shop.online.shopingMall.domain.CartItem;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;

@SpringBootTest
class CartServiceTest {

    @Autowired
    private CartService cartService;

    @DisplayName("장바구니 저장하고 값가져오기 (레디스에서 값가져오기) ")
    @Test
    public void 장바구니_저장한후_값가져오기() {
        //given
        CartItem item = CartItem.builder().id("test").color("red").size("95").price("100").build();
        Cart cart = Cart.builder().cartItems(Collections.singletonList(item)).id("test").build();

        // when
        cartService.save(cart);

        // then
        Cart test = cartService.findById("test");
        Assertions.assertThat(cart.getName()).isEqualTo(test.getName());
    }
}