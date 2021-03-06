package com.shop.online.shopingMall.dto.product;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.shop.online.shopingMall.domain.Product;
import com.shop.online.shopingMall.domain.User;
import lombok.*;

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

    public static Product toEntity(ProductSaveRequestDto requestDto, User user) {
        return Product.builder()
                .name(requestDto.getName()).user(user).description(requestDto.getDescription()).build();
    }
}
