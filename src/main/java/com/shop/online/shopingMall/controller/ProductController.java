package com.shop.online.shopingMall.controller;

import com.shop.online.shopingMall.Exception.NotFoundUserException;
import com.shop.online.shopingMall.dto.product.ProductDetailResponseDto;
import com.shop.online.shopingMall.dto.product.ProductSaveRequestDto;
import com.shop.online.shopingMall.service.ProductService;
import javassist.NotFoundException;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
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

        return ResponseEntity.ok().body("제품 등록에 성공 하였습니다.");
    }

    @GetMapping("/{id}")
    public ResponseEntity productDetail(@PathVariable @NonNull Long id) {
        ProductDetailResponseDto responseDto = productService.findProduct(id);
        return ResponseEntity.ok().body(responseDto);
    }
}
