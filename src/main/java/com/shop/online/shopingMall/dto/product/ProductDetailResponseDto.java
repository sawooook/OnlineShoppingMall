package com.shop.online.shopingMall.dto.product;

import com.shop.online.shopingMall.domain.ProductOption;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data @Builder
@AllArgsConstructor @NoArgsConstructor
public class ProductDetailResponseDto {

    private Long id;

    private String name;
    private String description;
    private List<ProductOptionDto> productOptions;
    private ProductPriceDto productPriceDto;
    private String sellerName;
}
