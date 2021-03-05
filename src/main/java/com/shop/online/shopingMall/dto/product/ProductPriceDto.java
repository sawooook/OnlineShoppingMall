package com.shop.online.shopingMall.dto.product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@AllArgsConstructor @NoArgsConstructor
public class ProductPriceDto {
    @NonNull
    private int price;
}
