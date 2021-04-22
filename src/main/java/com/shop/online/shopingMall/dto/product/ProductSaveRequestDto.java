package com.shop.online.shopingMall.dto.product;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.shop.online.shopingMall.domain.Product;
import com.shop.online.shopingMall.domain.ProductPrice;
import com.shop.online.shopingMall.domain.User;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

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

    public ProductSaveRequestDto(Product product) {
        this.userId = product.getUser().getId();
        this.name = product.getName();
        this.description = product.getDescription();
        this.productOptionDto = product.getProductOptions().stream().map(productOption -> new ProductOptionDto(productOption.getColor(), productOption.getSize())).collect(Collectors.toList());
        this.productPriceDto = new ProductPriceDto(product.lastRegisterPrice());
    }
}
