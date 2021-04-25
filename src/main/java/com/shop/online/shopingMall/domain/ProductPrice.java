package com.shop.online.shopingMall.domain;

import com.shop.online.shopingMall.domain.base.BaseEntity;
import com.shop.online.shopingMall.dto.product.ProductPriceDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/*
* 가격이 수정될 수 있기때문에 기록을 남기기위해
* 생성한 테이블
* */

@Entity @Builder @Getter
@NoArgsConstructor @AllArgsConstructor
public class ProductPrice extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_price_id")
    private Long id;

    private int price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_Id")
    private Product product;

    public ProductPrice(int price) {
        this.price = price;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
