package com.shop.online.shopingMall.dto.cart;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.shop.online.shopingMall.domain.Cart;
import com.shop.online.shopingMall.domain.CartItem;
import com.shop.online.shopingMall.dto.product.ProductOptionDto;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data @Builder
@NoArgsConstructor @AllArgsConstructor
public class CartRequestDto {
    private String id;
    private String name;

    @JsonProperty("product_options")
    private List<CartItemDto> cartItems;


    public static CartRequestDto toDto(CartRequestDto cartRequestDto) {
        List<CartItemDto> cartItems = cartRequestDto.getCartItems();
        List<CartItemDto> itemList = new ArrayList<>();

        for (CartItemDto cartItem : cartItems) {
            CartItemDto item = CartItemDto.builder().color(cartItem.getColor()).size(cartItem.getSize()).build();
            itemList.add(item);
        }
        return CartRequestDto.builder().id(cartRequestDto.getId()).name(cartRequestDto.getName()).cartItems(itemList).build();
    }

    public static Cart toEntity(CartRequestDto cartRequestDto) {
        List<CartItemDto> cartItems = cartRequestDto.getCartItems();
        List<CartItem> itemListEntity = new ArrayList<>();

        for (CartItemDto cartItem : cartItems) {
            CartItem item = CartItem.builder().price(cartItem.getPrice()).color(cartItem.getColor()).size(cartItem.getSize()).build();
            itemListEntity.add(item);
        }

        return Cart.builder().id(cartRequestDto.getId()).name(cartRequestDto.getName()).cartItems(itemListEntity).build();
    }
}
