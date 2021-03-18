package com.shop.online.shopingMall.dto.product;

import com.fasterxml.jackson.annotation.JsonProperty;
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
    @JsonProperty("low_price")
    private boolean lowPrice;
    @JsonProperty("high_price")
    private boolean highPrice;
    @JsonProperty("search_name")
    private String searchName;

    @QueryProjection
    public ProductSearchRequestDto(ProductCategory type, boolean lowPrice, boolean highPrice, String searchName) {
        this.type = type;
        this.lowPrice = lowPrice;
        this.highPrice = highPrice;
        this.searchName = searchName;
    }
}

