package com.shop.online.shopingMall.domain;

import com.shop.online.shopingMall.domain.base.BaseEntity;
import com.shop.online.shopingMall.domain.enumType.BillingInfoStatus;

import javax.persistence.*;
import java.util.ArrayList;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "billingInfo")
    private List<Order> orderList = new ArrayList<>();
}
