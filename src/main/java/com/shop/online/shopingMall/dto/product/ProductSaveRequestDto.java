package com.shop.online.shopingMall.dto.product;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.shop.online.shopingMall.domain.ProductOption;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.List;

@Data
@AllArgsConstructor @NoArgsConstructor
public class ProductSaveRequestDto {

    @NonNull
    private Long userId;

    @NonNull
    private String name;

    @NonNull
    private String description;

    @NonNull
    @JsonProperty("product_option")
    private List<ProductOptionDto> productOptionDto;

    @NonNull
    @JsonProperty("product_price")
    private ProductPriceDto productPriceDto;
}
