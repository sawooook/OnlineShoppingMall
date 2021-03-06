package com.shop.online.shopingMall.domain;


import com.shop.online.shopingMall.domain.base.BaseEntity;
import com.shop.online.shopingMall.domain.enumType.CardName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity @Getter
@NoArgsConstructor
public class Payment extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "payment_id")
    private Long id;

    // 결제 고유번호
    private String tid;

    // 요청 고유번호
    private String aid;

    // 가맹점코드
    private String cid;

    // 결제금액
    private String amount;

    // 상품 이름
    private String itemName;

    // 상품 수량
    private String quantity;

    // 결제 PG사
    @Enumerated(EnumType.STRING)
    private CardName cardName;

    // 결제 승인시각
    @Column(name = "approved_at")
    private LocalDateTime approvedAt;

    // 결제 준비요청 시각
    @Column(name = "payment_created_at")
    private LocalDateTime paymentCreatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "billingInfo_id")
    private BillingInfo billingInfo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    @Builder
    public Payment(String tid, String aid, String cid, String amount, String itemName, String quantity, CardName cardName, LocalDateTime approvedAt, LocalDateTime createdAt, BillingInfo billingInfo, Order order) {
        this.tid = tid;
        this.aid = aid;
        this.cid = cid;
        this.amount = amount;
        this.itemName = itemName;
        this.quantity = quantity;
        this.cardName = cardName;
        this.approvedAt = approvedAt;
        this.paymentCreatedAt = createdAt;
        this.billingInfo = billingInfo;
        this.order = order;
    }
}
