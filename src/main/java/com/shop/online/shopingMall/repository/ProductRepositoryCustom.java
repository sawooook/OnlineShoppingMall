package com.shop.online.shopingMall.repository;

import com.shop.online.shopingMall.dto.product.ProductSearchRequestDto;
import com.shop.online.shopingMall.dto.product.ProductSearchResponseDto;

import java.util.List;

public interface ProductRepositoryCustom {
    List<ProductSearchResponseDto> searchProduct(ProductSearchRequestDto productSearchDto);
}
