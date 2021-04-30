package com.shop.online.shopingMall.dto.cart;

import com.shop.online.shopingMall.domain.CartItem;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CartItemDto {
    private String size;
    private String color;
    private String price;

    public CartItemDto(CartItem cartItem) {
        this.size = cartItem.getSize();
        this.color = cartItem.getColor();
        this.price = cartItem.getPrice();
    }
}
