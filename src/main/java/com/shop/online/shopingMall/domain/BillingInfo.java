package com.shop.online.shopingMall.domain;

import com.shop.online.shopingMall.domain.base.BaseEntity;
import com.shop.online.shopingMall.domain.enumType.BillingInfoStatus;
import com.shop.online.shopingMall.domain.enumType.CardName;
import com.shop.online.shopingMall.domain.enumType.UserStatus;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Entity @Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BillingInfo extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "billingInfo_id")
    private Long id;

    // 등록한 카드의 종류
    @Enumerated(EnumType.STRING)
    private CardName cardName;

    // 결제시 사용하는 키번호
    private String paymentKey;

    // 결제 카드 고유 등록번호
    private String uniqueNumber;

    //결제정보의 상태
    @Enumerated(EnumType.STRING)
    private BillingInfoStatus billingInfoStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "billingInfo")
    private List<Order> orderList = new ArrayList<>();

    @OneToMany(mappedBy = "billingInfo")
    private List<Payment> payments = new ArrayList<>();

    public BillingInfo(CardName cardName, String tid, User user) {
        this.cardName = cardName;
        this.uniqueNumber = tid;
        this.user = user;
        this.billingInfoStatus = BillingInfoStatus.ACTIVE;
    }

    public void updatePaymentKey(String paymentKey) {
        this.paymentKey = paymentKey;
    }

    public boolean isActiveBillingInfo() {
        return this.billingInfoStatus == BillingInfoStatus.ACTIVE;
    }

    public void updateInActive() {
        this.billingInfoStatus = BillingInfoStatus.INACTIVE;
    }

    public void delete() {
        this.paymentKey = null;
        this.billingInfoStatus = BillingInfoStatus.INACTIVE;
    }
}
