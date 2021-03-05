package com.shop.online.shopingMall.service;

import com.shop.online.shopingMall.domain.Product;
import com.shop.online.shopingMall.domain.ProductPrice;
import com.shop.online.shopingMall.domain.User;
import com.shop.online.shopingMall.dto.product.ProductOptionDto;
import com.shop.online.shopingMall.dto.product.ProductPriceDto;
import com.shop.online.shopingMall.dto.product.ProductSaveRequestDto;
import javassist.NotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@Transactional
class ProductServiceTest {

    @Autowired
    private ProductService productService;

    @Autowired
    private EntityManager entityManager;

    @Test
    @DisplayName("제품 등록 테스트 - 옵션등록 안했을시 발생하는 에러")
    public void 옵션등록_X() throws ChangeSetPersister.NotFoundException, NotFoundException {
        User testUser = User.builder().name("test").build();
        entityManager.persist(testUser);

        ProductSaveRequestDto productSaveRequestDto = new ProductSaveRequestDto();
        productSaveRequestDto.setName("test");
        productSaveRequestDto.setDescription("test");
        productSaveRequestDto.setUserId(testUser.getId());
        productSaveRequestDto.setProductPriceDto(new ProductPriceDto(100));

        NotFoundException exception = assertThrows(NotFoundException.class, () ->
                productService.saveProduct(productSaveRequestDto));

        String message = exception.getMessage();
        assertEquals("옵션을 선택하지 않았습니다", message);

    }

}