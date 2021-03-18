package com.shop.online.shopingMall.dto.product;

import com.querydsl.core.annotations.QueryProjection;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder @NoArgsConstructor
public class ProductSearchRequestDto {
    private String type;
    private boolean lowPrice;
    private boolean highPrice;
    private String searchName;

    @QueryProjection
    public ProductSearchRequestDto(String type, boolean lowPrice, boolean highPrice, String searchName) {
        this.type = type;
        this.lowPrice = lowPrice;
        this.highPrice = highPrice;
        this.searchName = searchName;
    }
}

