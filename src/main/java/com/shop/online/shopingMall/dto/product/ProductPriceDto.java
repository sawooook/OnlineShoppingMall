package com.shop.online.shopingMall.dto.product;

import com.shop.online.shopingMall.domain.ProductPrice;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@AllArgsConstructor @NoArgsConstructor
public class ProductPriceDto {
    private int price;

    public static ProductPrice toEntity(ProductPriceDto productPriceDto) {
        return ProductPrice.builder().price(productPriceDto.getPrice()).build();
    }
}
