package com.shop.online.shopingMall.domain;

import com.shop.online.shopingMall.domain.base.BaseEntity;
import com.shop.online.shopingMall.domain.enumType.OrderStatus;

import javax.persistence.*;
import java.util.Base64;

@Entity
public class Order extends BaseEntity {

    @Id @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @OneToMany(mappedBy = "billingInfo_id")
    private BillingInfo billingInfo;

    @OneToOne(fetch = FetchType.LAZY)
    private Delivery delivery;

}
