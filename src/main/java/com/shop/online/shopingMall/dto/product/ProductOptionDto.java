package com.shop.online.shopingMall.dto.product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@AllArgsConstructor @NoArgsConstructor
public class ProductOptionDto {

    @NonNull
    private String color;

    @NonNull
    private String size;
}
