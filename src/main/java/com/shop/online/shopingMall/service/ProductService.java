package com.shop.online.shopingMall.service;

import com.shop.online.shopingMall.domain.Product;
import com.shop.online.shopingMall.domain.ProductOption;
import com.shop.online.shopingMall.domain.ProductPrice;
import com.shop.online.shopingMall.domain.User;
import com.shop.online.shopingMall.dto.product.ProductDetailResponseDto;
import com.shop.online.shopingMall.dto.product.ProductPriceDto;
import com.shop.online.shopingMall.dto.product.ProductSaveRequestDto;
import com.shop.online.shopingMall.repository.ProductPriceRepository;
import com.shop.online.shopingMall.repository.ProductRepository;
import com.shop.online.shopingMall.repository.ProductionOptionRepository;
import com.shop.online.shopingMall.repository.UserRepository;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class ProductService {

    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final ProductionOptionRepository productionOptionRepository;
    private final ProductPriceRepository productPriceRepository;

    /*
    * 제품 저장
    *
    * */

    public Product saveProduct(ProductSaveRequestDto productSaveRequestDto) throws NotFoundException {
        User findUser = userRepository.findById(productSaveRequestDto.getUserId()).orElseThrow(() -> new NotFoundException("회원정보를 찾을 수 없습니다"));

        Product buildProduct = Product.builder()
                .user(findUser).description(productSaveRequestDto.getDescription()).name(productSaveRequestDto.getName()).build();

        Product makeProduct = productRepository.save(buildProduct);

        if (productSaveRequestDto.getProductOptionDto() == null) {
            throw new NotFoundException("옵션을 선택하지 않았습니다");
        }
        int productOptionSize = productSaveRequestDto.getProductOptionDto().size();

        for (int i = 0; i < productOptionSize ; i++) {
            ProductOption productOption = ProductOption.saveProductOption(
                    productSaveRequestDto.getProductOptionDto().get(i).getColor(),
                    productSaveRequestDto.getProductOptionDto().get(i).getSize(),
                    makeProduct);
            productionOptionRepository.save(productOption);
        }

        ProductPrice makeProductPrice = ProductPrice.saveProductPrice(makeProduct, productSaveRequestDto.getProductPriceDto().getPrice());

        productPriceRepository.save(makeProductPrice);

        return makeProduct;
    }

    public Product findProduct(Long id) throws NotFoundException {
        Product product = productRepository.findById(id).orElseThrow(() -> new NotFoundException("제품을 찾을 수 없습니다"));

        User seller = product.getUser();

        ProductDetailResponseDto.builder()
                .description(product.getDescription()).name(product.getName()).sellerName(seller.getName())
                .productPriceDto(new ProductPriceDto(product.lastRegisterPrice()));

    }

}
