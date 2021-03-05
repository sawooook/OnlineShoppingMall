package com.shop.online.shopingMall.domain;

import com.shop.online.shopingMall.domain.User;
import com.shop.online.shopingMall.domain.base.BaseEntity;
import com.shop.online.shopingMall.domain.enumType.ProductStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity @Builder @Getter
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "product")
    private List<ProductOption> productOptions = new ArrayList<>();

    @OneToMany(mappedBy = "product")
    private List<ProductPrice> productPrices = new ArrayList<>();

    @PrePersist
    public void prePersist() {
        if (this.getProductStatus() == null) {
            this.productStatus = ProductStatus.ACTIVE;
        }
    }
}
