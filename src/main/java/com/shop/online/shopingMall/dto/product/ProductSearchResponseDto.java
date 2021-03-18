package com.shop.online.shopingMall.dto.product;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;

@Data
public class ProductSearchResponseDto {
    private Long id;
    private String name;
    private int price;

    @QueryProjection
    public ProductSearchResponseDto(Long id, String name, int price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }
}
