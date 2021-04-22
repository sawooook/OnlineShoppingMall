package com.shop.online.shopingMall.domain;

import com.shop.online.shopingMall.domain.base.BaseEntity;
import com.shop.online.shopingMall.domain.enumType.ProductOptionStatus;
import com.shop.online.shopingMall.dto.product.ProductOptionDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.swing.text.html.Option;
import java.util.List;

@Entity @Builder @Getter
@NoArgsConstructor @AllArgsConstructor
public class ProductOption extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    public void setProduct(Product product) {
        this.product = product;
    }
}
