package com.shop.online.shopingMall.domain;

import com.shop.online.shopingMall.domain.base.BaseEntity;
import com.shop.online.shopingMall.domain.enumType.ProductStatus;
import com.shop.online.shopingMall.dto.product.ProductOptionDto;
import com.shop.online.shopingMall.dto.product.ProductPriceDto;
import com.shop.online.shopingMall.dto.product.ProductSaveRequestDto;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity @Getter
@NoArgsConstructor @AllArgsConstructor
public class Product extends BaseEntity {

    @Id @GeneratedValue
    @Column(name = "product_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    private ProductStatus productStatus;

    // 제품 명
    private String name;

    //제품 설명
    private String description;

    @Enumerated(EnumType.STRING)
    private ProductCategory category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<ProductOption> productOptions = new ArrayList<>();

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<ProductPrice> productPrices = new ArrayList<>();

    @OneToMany(mappedBy = "product")
    private List<Order> orderList = new ArrayList<>();

    @PrePersist
    public void prePersist() {
        if (this.getProductStatus() == null) {
            this.productStatus = ProductStatus.ACTIVE;
        }
    }

    @Builder
    public Product(Long id, ProductStatus productStatus, String name, String description, User user) {
        this.id = id;
        this.productStatus = productStatus;
        this.name = name;
        this.description = description;
        this.user = user;
    }


    public static Product createProduct(Product product, List<ProductOption> productOptions, ProductPrice productPrice) {

        for (ProductOption option : productOptions) {
            product.addProductOption(option);
        }

        product.addProductPrice(productPrice);

        return product;
    }

    private void addProductPrice(ProductPrice price) {
        productPrices.add(price);
        price.setProduct(this);
    }

    private void addProductOption(ProductOption option) {
        productOptions.add(option);
        option.setProduct(this);
    }




    public int lastRegisterPrice() {
        int lastPriceSize = getProductPrices().size() - 1;
        return getProductPrices().get(lastPriceSize).getPrice();
    }

    public List<ProductOption> createProductOption(List<ProductOptionDto> productOptionDto) {

        return productOptions;
    }

    public List<ProductPrice> createProductPrice(ProductPriceDto productPriceDto) {
        ProductPrice price = ProductPrice.saveProductPrice(productPriceDto, this);
        productPrices.add(price);
        return productPrices;
    }

//    public void createProductOption(ProductSaveRequestDto requestDto) {
//        for (int i = 0; i < requestDto.getProductOptionDto().size() -1 ; i++) {
//            productOptions.add(requestDto.g)
//            productOptions.add();
//        }
//    }
}
