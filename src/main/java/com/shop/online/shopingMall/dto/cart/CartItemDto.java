package com.shop.online.shopingMall.dto.cart;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @Builder
@AllArgsConstructor @NoArgsConstructor
public class CartItemDto {
    private String size;
    private String color;
    private String price;
}
