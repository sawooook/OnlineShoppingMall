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

@Data @Builder
@NoArgsConstructor @AllArgsConstructor
public class CartResponseDto {
    private String name;
    @JsonProperty("product_options")
    private List<CartItemDto> cartItem;

    public static CartResponseDto toDto(Cart cart) {
        List<CartItem> cartItems = cart.getCartItems();
        List<CartItemDto> cartItemDtos = new ArrayList<>();

        for (CartItem cartItem : cartItems) {
            CartItemDto item = CartItemDto.builder()
                    .color(cartItem.getColor()).size(cartItem.getSize()).build();
            cartItemDtos.add(item);
        }

        return CartResponseDto.builder()
                .name(cart.getName()).cartItem(cartItemDtos).build();
    }
}
