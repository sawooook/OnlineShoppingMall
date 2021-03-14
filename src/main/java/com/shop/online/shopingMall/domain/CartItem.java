package com.shop.online.shopingMall.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import javax.persistence.Id;

@Getter
@EqualsAndHashCode
@Builder
@AllArgsConstructor
public class CartItem {

    @Id
    private String id;
    private String color;
    private String price;
    private String size;
}
