package com.shop.online.shopingMall.controller;

import com.shop.online.shopingMall.concern.ResponseMessage;
import com.shop.online.shopingMall.concern.ResponseStatus;
import com.shop.online.shopingMall.domain.Product;
import com.shop.online.shopingMall.dto.product.ProductSearchRequestDto;
import com.shop.online.shopingMall.dto.product.ProductSearchResponseDto;
import com.shop.online.shopingMall.dto.product.ProductDto;
import com.shop.online.shopingMall.service.ProductService;
import lombok.NonNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity productSave(@RequestBody ProductDto productDto) {
        Product product = productService.saveProduct(productDto);
        ProductDto response = new ProductDto(product);
        return ResponseEntity.ok().body(new ResponseMessage(ResponseStatus.OK, response));
    }

    /**
     * 제품상세정보 관련 API
     *
     * 성공시 return OK, product 정보
     * 실패시 return BadRequest
     *
     * */
    @GetMapping("/{id}")
    public ResponseEntity productDetail(@PathVariable @NonNull Long id) {
        Product product = productService.findProduct(id);
        ProductDto productDto = new ProductDto(product);
        return ResponseEntity.ok().body(new ResponseMessage(ResponseStatus.OK, productDto));
    }

    @GetMapping("/list")
    public ResponseEntity productList(@RequestBody ProductSearchRequestDto productSearchRequestDto) {
        List<ProductSearchResponseDto> response = productService.productList(productSearchRequestDto);
        return ResponseEntity.ok().body(new ResponseMessage(ResponseStatus.OK, response));
    }
}
