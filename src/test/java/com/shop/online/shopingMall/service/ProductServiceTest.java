//package com.shop.online.shopingMall.service;
//
//import com.shop.online.shopingMall.domain.User;
//import com.shop.online.shopingMall.dto.product.ProductPriceDto;
//import com.shop.online.shopingMall.dto.product.ProductDto;
//import javassist.NotFoundException;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.data.crossstore.ChangeSetPersister;
//import org.springframework.transaction.annotation.Transactional;
//
//import javax.persistence.EntityManager;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//
//@SpringBootTest
//@Transactional
//class ProductServiceTest {
//
//    @Autowired
//    private ProductService productService;
//
//    @Autowired
//    private EntityManager entityManager;
//
//    @Test
//    @DisplayName("제품 등록 테스트 - 옵션등록 안했을시 발생하는 에러")
//    public void 옵션등록_X() throws ChangeSetPersister.NotFoundException, NotFoundException {
//        User testUser = User.builder().name("test").build();
//        entityManager.persist(testUser);
//
//        ProductDto productDto = new ProductDto();
//        productDto.setName("test");
//        productDto.setDescription("test");
//        productDto.setUserId(testUser.getId());
//        productDto.setProductPriceDto(new ProductPriceDto(100));
//
//        NotFoundException exception = assertThrows(NotFoundException.class, () ->
//                productService.saveProduct(productDto));
//
//        String message = exception.getMessage();
//        assertEquals("옵션을 선택하지 않았습니다", message);
//
//    }
//
//}