package com.shop.online.shopingMall.domain;

import com.shop.online.shopingMall.dto.cart.CartItemDto;
import lombok.*;
import org.springframework.data.redis.core.RedisHash;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.List;

@Getter
@Builder
@RedisHash("cart") // key 설정
public class Cart implements Serializable {

    @Id
    private String id;
    private String name;
    private List<CartItem> cartItems;

    public Cart(String id, String name, List<CartItem> cartItems) {
        this.id = id;
        this.name = name;
        this.cartItems = cartItems;
    }
}
