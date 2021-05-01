package com.shop.online.shopingMall.dto.product;

import com.shop.online.shopingMall.domain.ProductPrice;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
public class ProductPriceDto {
    private int price;

    public ProductPriceDto(int price) {
        this.price = price;
    }
}
