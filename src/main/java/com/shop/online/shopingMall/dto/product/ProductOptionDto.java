package com.shop.online.shopingMall.dto.product;

import com.shop.online.shopingMall.domain.Product;
import com.shop.online.shopingMall.domain.ProductOption;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data @Builder
@NoArgsConstructor
public class ProductOptionDto {

    @NonNull
    private String color;

    @NonNull
    private String size;

    public ProductOptionDto(String color, String size) {
        this.color = color;
        this.size = size;
    }

    public static List<ProductOption> toEntity(List<ProductOptionDto> productOptionDto) {
        List<ProductOption> productOptionList = new ArrayList<>();

        for (int i = 0; i < productOptionDto.size(); i++) {
            ProductOption option = ProductOption.builder()
                    .color(productOptionDto.get(i).getColor()).size(productOptionDto.get(i).getSize()).build();

            productOptionList.add(option);
        }
        return productOptionList;
    }
}
