package com.shop.online.shopingMall.dto.product;

import com.shop.online.shopingMall.domain.Product;
import com.shop.online.shopingMall.domain.ProductOption;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data @Builder
@AllArgsConstructor @NoArgsConstructor
public class ProductDetailResponseDto {

    private String name;
    private String description;
    private List<ProductOptionDto> productOptions;
    private int productPrice;
    private String sellerName;


    public static ProductDetailResponseDto toDto(Product product) {
        List<ProductOptionDto> productOptionDtos = ProductOptionDto.toDto(product.getProductOptions());

        return ProductDetailResponseDto.builder()
                .name(product.getName()).description(product.getDescription())
                .productPrice(product.lastRegisterPrice()).sellerName(product.getUser().getName())
                .productOptions(productOptionDtos).build();
    }
}
