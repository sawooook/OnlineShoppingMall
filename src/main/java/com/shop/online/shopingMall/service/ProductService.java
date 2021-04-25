package com.shop.online.shopingMall.service;

import com.shop.online.shopingMall.domain.enumType.ProductStatus;
import com.shop.online.shopingMall.domain.enumType.UserStatus;
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
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class ProductService {

    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    /**
    * 제품 저장
    * 요청을 통해 들어온 제품을 저장한다.
    * */
    @Transactional
    public Product saveProduct(ProductDto requestDto) {

        User findUser = userRepository.findByIdAndUserStatus(requestDto.getUserId(), UserStatus.SIGN).orElseThrow(NotFoundUserException::new);
        Product product = new Product(requestDto, findUser);

        List<ProductOption> options = requestDto.getProductOptionDto().stream().map(
                productOptionDto -> new ProductOption(productOptionDto.getSize(), productOptionDto.getColor())).collect(Collectors.toList());

        product.addOptionAndPrice(options, new ProductPrice(requestDto.getProductPriceDto().getPrice()));
        return productRepository.save(product);
    }


    /**
    * - 제품 상세페이지
    * 제품을 찾으면 제품에대한 상세페이지 리스트를 리턴해준다.
    *
     * @return*/
    public ProductDto findProduct(Long id) throws ProductNotFoundException {
        Product product = productRepository.findByIdAndProductStatus(id, ProductStatus.ACTIVE).orElseThrow(ProductNotFoundException::new);
        ProductDto productDto = new ProductDto(product);
        return productDto;
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
