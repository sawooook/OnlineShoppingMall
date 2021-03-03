package com.shop.online.shopingMall.domain;

import com.shop.online.shopingMall.domain.base.BaseEntity;
import com.shop.online.shopingMall.domain.enumType.BillingInfoStatus;

import javax.persistence.*;
import java.util.List;

@Entity
public class BillingInfo extends BaseEntity {

    @Id @GeneratedValue
    @Column(name = "billingInfo_id")
    private Long id;

    private String name;

    private String cardNum;
    private String bid;
    private BillingInfoStatus billingInfoStatus;

    @OneToMany(mappedBy = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    private List<Order> orderList;
}
