package com.shop.online.shopingMall.service;

import com.shop.online.shopingMall.dto.product.*;
import com.shop.online.shopingMall.exception.NotFoundUserException;
import com.shop.online.shopingMall.exception.ProductNotFoundException;
import com.shop.online.shopingMall.domain.Product;
import com.shop.online.shopingMall.domain.ProductOption;
import com.shop.online.shopingMall.domain.ProductPrice;
import com.shop.online.shopingMall.domain.User;
import com.shop.online.shopingMall.repository.ProductRepository;
import com.shop.online.shopingMall.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ProductService {

    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    /*
    * 제품 저장
    *
    * */
    public void saveProduct(ProductSaveRequestDto requestDto) throws NotFoundUserException {

        User findUser = userRepository.findById(requestDto.getUserId())
                .orElseThrow(() -> new NotFoundUserException("회원정보를 찾을 수 없습니다"));

        Product product = ProductSaveRequestDto.toEntity(requestDto, findUser);
        List<ProductOption> optionsList = ProductOptionDto.toEntity(requestDto.getProductOptionDto());
        ProductPrice productPrice = ProductPriceDto.toEntity(requestDto.getProductPriceDto());

        Product makeProduct = Product.createProduct(product, optionsList, productPrice);

        productRepository.save(makeProduct);
    }


    /**
    * - 제품 상세페이지
    * 제품을 찾으면 제품에대한 상세페이지 리스트를 리턴해준다.
    * */
    public ProductDetailResponseDto findProduct(Long id) throws ProductNotFoundException {

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("제품을 찾을 수 없습니다"));

        return ProductDetailResponseDto.toDto(product);
    }

    /**
     * 검색 타겟에 맞춰서 제품 리스트를 가져옴
    *
     * @return*/
    public List<ProductSearchResponseDto> productList(ProductSearchRequestDto productSearchRequestDto) {
        List<ProductSearchResponseDto> response = productRepository.searchProduct(productSearchRequestDto);

        for (ProductSearchResponseDto productSearchResponseDto : response) {
            System.out.println("productSearchResponseDto = " + productSearchResponseDto);
        }

        return response;
    }
}
