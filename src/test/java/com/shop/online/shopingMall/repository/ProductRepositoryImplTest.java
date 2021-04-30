package com.shop.online.shopingMall.repository;

import com.shop.online.shopingMall.domain.Product;
import com.shop.online.shopingMall.domain.ProductCategory;
import com.shop.online.shopingMall.domain.ProductPrice;
import com.shop.online.shopingMall.domain.User;
import com.shop.online.shopingMall.domain.enumType.ProductStatus;
import com.shop.online.shopingMall.domain.enumType.UserRole;
import com.shop.online.shopingMall.dto.product.ProductSearchRequestDto;
import com.shop.online.shopingMall.dto.product.ProductSearchResponseDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class ProductRepositoryImplTest {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProductPriceRepository productPriceRepository;

    @BeforeEach
    void setting() {
        User user = User.builder().name("test").userRole(UserRole.seller).email("test").phone("test").build();
        userRepository.save(user);


        Product product1 = Product.builder().name("test1").category(ProductCategory.COAT).user(user).productStatus(ProductStatus.ACTIVE).build();
        Product product2 = Product.builder().name("test2").category(ProductCategory.COAT).user(user).productStatus(ProductStatus.ACTIVE).build();
        Product product3 = Product.builder().name("test3").category(ProductCategory.PANTS).user(user).productStatus(ProductStatus.INACTIVE).build();
        Product product4 = Product.builder().name("test4").category(ProductCategory.SKIRT).user(user).productStatus(ProductStatus.ACTIVE).build();

        ProductPrice price1 = ProductPrice.builder().price(1000).product(product1).build();
        ProductPrice price2 = ProductPrice.builder().price(2000).product(product2).build();
        ProductPrice price3 = ProductPrice.builder().price(3000).product(product3).build();
        ProductPrice price4 = ProductPrice.builder().price(4000).product(product4).build();

        productRepository.save(product1);
        productRepository.save(product2);
        productRepository.save(product3);
        productRepository.save(product4);

        productPriceRepository.save(price1);
        productPriceRepository.save(price2);
        productPriceRepository.save(price3);
        productPriceRepository.save(price4);
    }

    @Test
    public void 제품_필터_테스트_높은가격순_정렬() {
        ProductSearchRequestDto request = ProductSearchRequestDto.builder().highPrice(true).lowPrice(false).searchName(null).type(null).build();
        List<ProductSearchResponseDto> products = productRepository.searchProduct(request);

        org.assertj.core.api.Assertions.assertThat(products.get(0).getPrice()).isEqualTo(4000);
    }

    @Test
    public void 제품_필터_테스트_낮은가격순_정렬() {
        ProductSearchRequestDto request = ProductSearchRequestDto.builder().highPrice(false).lowPrice(true).searchName(null).type(null).build();
        List<ProductSearchResponseDto> products = productRepository.searchProduct(request);

        org.assertj.core.api.Assertions.assertThat(products.get(0).getPrice()).isEqualTo(1000);
    }

    @Test
    public void 제품_필터_테스트__제품이름으로찾기() {
        ProductSearchRequestDto request = ProductSearchRequestDto.builder().highPrice(false).lowPrice(true).searchName("test1").type(null).build();
        List<ProductSearchResponseDto> products = productRepository.searchProduct(request);
        org.assertj.core.api.Assertions.assertThat(products.get(0).getName()).isEqualTo("test1");
    }

    @Test
    public void 제품_필터_테스트_카테고리분류() {
        ProductSearchRequestDto request = ProductSearchRequestDto.builder().highPrice(false).lowPrice(true).searchName(null).type(ProductCategory.COAT).build();
        List<ProductSearchResponseDto> products = productRepository.searchProduct(request);
        org.assertj.core.api.Assertions.assertThat(products.size()).isEqualTo(2);
    }

    @Test
    public void N_1_TEST() {
        productRepository.findAll();
    }

}