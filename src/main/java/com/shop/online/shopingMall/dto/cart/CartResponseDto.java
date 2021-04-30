package com.shop.online.shopingMall.dto.cart;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.shop.online.shopingMall.domain.Cart;
import com.shop.online.shopingMall.domain.CartItem;
import com.shop.online.shopingMall.domain.Product;
import com.shop.online.shopingMall.domain.ProductOption;
import com.shop.online.shopingMall.dto.product.ProductOptionDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class CartResponseDto {
    private String name;
    @JsonProperty("product_options")
    private List<CartItemDto> cartItem;

    public CartResponseDto(Cart cart) {
        this.name = cart.getName();
        this.cartItem = cart.getCartItems()
                .stream()
                .map(cartItem -> new CartItemDto(cartItem))
                .collect(Collectors.toList());
    }
}
