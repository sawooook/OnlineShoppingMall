package com.shop.online.shopingMall.dto.product;

import com.querydsl.core.annotations.QueryProjection;
import com.shop.online.shopingMall.domain.ProductCategory;
import lombok.Data;

@Data
public class ProductSearchResponseDto {
    private Long id;
    private String name;
    private int price;
    private ProductCategory category;


    @QueryProjection
    public ProductSearchResponseDto(Long id, String name, int price, ProductCategory category) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.category = category;
    }
}
