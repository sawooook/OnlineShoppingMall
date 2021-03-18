package com.shop.online.shopingMall.dto.product;

import com.querydsl.core.annotations.QueryProjection;
import com.shop.online.shopingMall.domain.ProductCategory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder @NoArgsConstructor
public class ProductSearchRequestDto {
    private ProductCategory type;
    private boolean lowPrice;
    private boolean highPrice;
    private String searchName;

    @QueryProjection
    public ProductSearchRequestDto(ProductCategory type, boolean lowPrice, boolean highPrice, String searchName) {
        this.type = type;
        this.lowPrice = lowPrice;
        this.highPrice = highPrice;
        this.searchName = searchName;
    }
}

