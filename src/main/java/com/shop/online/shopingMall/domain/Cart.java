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
@RedisHash("cart")
@NoArgsConstructor
@AllArgsConstructor
public class Cart implements Serializable {

    @Id
    private String id;
    private String name;
    private List<CartItem> cartItems;
}
