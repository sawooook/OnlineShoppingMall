package com.shop.online.shopingMall.domain;

import com.shop.online.shopingMall.domain.base.BaseEntity;
import com.shop.online.shopingMall.domain.enumType.ProductOptionStatus;
import com.shop.online.shopingMall.dto.product.ProductOptionDto;
import lombok.*;

import javax.persistence.*;
import javax.swing.text.html.Option;
import java.util.List;

@Entity @Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProductOption extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "product_option_id")
    private Long id;

    private String color;

    private String size;

    @Enumerated(EnumType.STRING)
    private ProductOptionStatus productOptionStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    public ProductOption(String size, String color) {
        this.size = size;
        this.color = color;
        this.productOptionStatus = ProductOptionStatus.ACTIVE;
    }

    @Builder
    public ProductOption(Long id, String color, String size, ProductOptionStatus productOptionStatus, Product product) {
        this.id = id;
        this.color = color;
        this.size = size;
        this.productOptionStatus = productOptionStatus;
        this.product = product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
