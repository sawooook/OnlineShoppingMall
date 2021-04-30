package com.shop.online.shopingMall.domain;

import lombok.*;

import javax.persistence.Id;

@Getter
@EqualsAndHashCode
@NoArgsConstructor
public class CartItem {

    @Id
    private String id;
    private String color;
    private String price;
    private String size;

    public CartItem(String color, String size, String price) {
        this.color = color;
        this.size = size;
        this.price = price;
    }
}
