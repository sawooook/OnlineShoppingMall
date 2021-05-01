package com.shop.online.shopingMall.controller;

import com.shop.online.shopingMall.domain.Product;
import com.shop.online.shopingMall.dto.product.ProductSearchRequestDto;
import com.shop.online.shopingMall.dto.product.ProductSearchResponseDto;
import com.shop.online.shopingMall.dto.product.ProductDto;
import com.shop.online.shopingMall.service.ProductService;
import lombok.NonNull;
import org.springframework.web.bind.annotation.*;
import com.shop.online.shopingMall.util.ApiResponse;

import java.util.List;

import static com.shop.online.shopingMall.util.ApiResponse.success;

@RestController
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    /**
     * 제품등록 관련 API
     *
     * 성공시 return OK, product 정보
     * 실패시 return BadRequest
     *
     * */
    @PostMapping
    public ApiResponse productSave(@RequestBody ProductDto productDto) {
        Product product = productService.saveProduct(productDto);
        ProductDto response = new ProductDto(product);
        return success(response);
    }

    /**
     * 제품상세정보 관련 API
     *
     * 성공시 return OK, product 정보
     * 실패시 return BadRequest
     *
     *
     * @return*/
    @GetMapping("/{id}")
    public ApiResponse<ProductDto> productDetail(@PathVariable @NonNull Long id) {
        ProductDto response = productService.findProduct(id);
        return success(response);
    }

    @GetMapping("/list")
    public ApiResponse<List<ProductSearchResponseDto>> productList(@RequestBody ProductSearchRequestDto productSearchRequestDto) {
        List<ProductSearchResponseDto> response = productService.productList(productSearchRequestDto);
        return success(response);
    }
}
