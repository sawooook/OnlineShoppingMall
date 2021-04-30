package com.shop.online.shopingMall.dto.cart;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.shop.online.shopingMall.domain.Cart;
import com.shop.online.shopingMall.domain.CartItem;
import com.shop.online.shopingMall.dto.product.ProductOptionDto;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor @AllArgsConstructor
public class CartRequestDto {
    private String id;
    private String name;

    @JsonProperty("product_options")
    private List<CartItemDto> cartItems;

}
