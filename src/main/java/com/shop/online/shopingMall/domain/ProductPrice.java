package com.shop.online.shopingMall.domain;

import com.shop.online.shopingMall.domain.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.security.core.parameters.P;

import javax.persistence.*;

/*
* 가격이 수정될 수 있기때문에 기록을 남기기위해
* 생성한 테이블
* */

@Entity @Builder
@NoArgsConstructor @AllArgsConstructor
public class ProductPrice extends BaseEntity {

    @Id @GeneratedValue
    @Column(name = "product_price_id")
    private Long id;

    private int price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_Id")
    private Product product;
}
