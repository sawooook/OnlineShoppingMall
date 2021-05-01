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

@Entity @Builder @Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BillingInfo extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "billingInfo_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    private CardName cardName;

    private String paymentKey;

    private String uniqueNumber;

    @Enumerated(EnumType.STRING)
    @ColumnDefault("'ACTIVE'")
    private BillingInfoStatus billingInfoStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "billingInfo")
    private List<Order> orderList = new ArrayList<>();

    @OneToMany(mappedBy = "billingInfo")
    private List<Payment> payments = new ArrayList<>();

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
