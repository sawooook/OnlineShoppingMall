package com.shop.online.shopingMall.domain;


import com.shop.online.shopingMall.domain.base.BaseEntity;
import com.shop.online.shopingMall.domain.enumType.CardName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity @Builder
@NoArgsConstructor @AllArgsConstructor
public class Payment extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "payment_id")
    private Long id;

    private String tid;
    private String aid;
    private String cid;
    private String amount;
    private String cardInfo;
    private String itemName;
    private String quantity;

    @Enumerated(EnumType.STRING)
    private CardName cardName;

    private LocalDateTime approveAt;
    private LocalDateTime createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "billingInfo_id")
    private BillingInfo billingInfo;
}
