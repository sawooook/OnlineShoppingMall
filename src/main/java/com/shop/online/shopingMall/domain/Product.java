package com.shop.online.shopingMall.domain;

import com.shop.online.shopingMall.domain.base.BaseEntity;
import com.shop.online.shopingMall.domain.enumType.ProductStatus;
import com.shop.online.shopingMall.dto.product.ProductDto;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    public Product(ProductDto requestDto, User user) {
        this.productStatus = ProductStatus.ACTIVE;
        this.name = requestDto.getName();
        this.description = requestDto.getDescription();
        this.category = ProductCategory.SHIRTS;
        this.user = user;
    }

    // 옵션과 가격을 product 객체에 담는다.
    public void addOptionAndPrice(List<ProductOption> options, ProductPrice price) {
        for (ProductOption option : options) {
            addProductOption(option);
        }
        addProductPrice(price);
    }

    private void addProductPrice(ProductPrice price) {
        productPrices.add(price);
        price.setProduct(this);
    }

    private void addProductOption(ProductOption option) {
        productOptions.add(option);
        option.setProduct(this);
    }


    // 마지막으로 등록된 제품의 가격을 불러옴
    public int lastRegisterPrice() {
        return getProductPrices().get(getProductPrices().size() - 1).getPrice();
    }
}
