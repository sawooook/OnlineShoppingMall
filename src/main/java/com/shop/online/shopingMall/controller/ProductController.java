package com.shop.online.shopingMall.controller;

import com.shop.online.shopingMall.concern.ResponseMessage;
import com.shop.online.shopingMall.concern.ResponseStatus;
import com.shop.online.shopingMall.exception.NotFoundUserException;
import com.shop.online.shopingMall.dto.product.ProductDetailResponseDto;
import com.shop.online.shopingMall.dto.product.ProductSaveRequestDto;
import com.shop.online.shopingMall.service.ProductService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity productSave(@RequestBody ProductSaveRequestDto productSaveRequestDto) throws NotFoundUserException {
        productService.saveProduct(productSaveRequestDto);

        return ResponseEntity.ok().body(new ResponseMessage(ResponseStatus.OK, "제품 등록에 성공 하였습니다", null));
    }

    @GetMapping("/{id}")
    public ResponseEntity productDetail(@PathVariable @NonNull Long id) {
        ProductDetailResponseDto responseDto = productService.findProduct(id);
        return ResponseEntity.ok().body(responseDto);
    }

    @GetMapping("/list")
    public ResponseEntity productList() {
        productService.allProduct();
        return ResponseEntity.ok().body("123");
    }
}
