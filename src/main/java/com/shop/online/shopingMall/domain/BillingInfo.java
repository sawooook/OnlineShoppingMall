package com.shop.online.shopingMall.domain;

import com.shop.online.shopingMall.domain.base.BaseEntity;
import com.shop.online.shopingMall.domain.enumType.BillingInfoStatus;
import com.shop.online.shopingMall.domain.enumType.CardName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity @Builder @Getter
@NoArgsConstructor @AllArgsConstructor
public class BillingInfo extends BaseEntity {

    @Id @GeneratedValue
    @Column(name = "billingInfo_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    private CardName cardName;

    private String paymentKey;

    private String uniqueNumber;

    private BillingInfoStatus billingInfoStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "billingInfo")
    private List<Order> orderList = new ArrayList<>();

    @OneToMany(mappedBy = "billingInfo")
    private List<Payment> payments = new ArrayList<>();
}
