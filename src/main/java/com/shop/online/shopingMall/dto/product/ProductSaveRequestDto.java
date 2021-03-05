package com.shop.online.shopingMall.dto.product;

import com.shop.online.shopingMall.domain.ProductOption;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor @NoArgsConstructor
public class ProductSaveRequestDto {

    private Long userId;
    private ProductOption productOption;
    private ProductPriceDto productPriceDto;

}
