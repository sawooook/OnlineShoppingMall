package com.shop.online.shopingMall.domain;

import com.shop.online.shopingMall.domain.base.BaseEntity;
import com.shop.online.shopingMall.domain.enumType.ProductOptionStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity @Builder @Getter
@NoArgsConstructor @AllArgsConstructor
public class ProductOption extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "product_option_id")
    private Long id;

    private String color;

    private String size;

    @Enumerated(EnumType.STRING)
    private ProductOptionStatus productOptionStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @PrePersist
    public void prePersist() {
        if (this.getProductOptionStatus() == null) {
            this.productOptionStatus = ProductOptionStatus.ACTIVE;
        }
    }

    public static ProductOption saveProductOption(String color, String size, Product product) {
        return ProductOption.builder()
                .product(product).size(size).color(color).build();
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
