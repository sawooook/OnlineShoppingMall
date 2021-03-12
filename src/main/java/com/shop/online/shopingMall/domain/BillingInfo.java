package com.shop.online.shopingMall.domain;

import com.shop.online.shopingMall.domain.base.BaseEntity;
import com.shop.online.shopingMall.domain.enumType.BillingInfoStatus;
import com.shop.online.shopingMall.domain.enumType.CardName;
import com.shop.online.shopingMall.domain.enumType.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Entity @Builder @Getter
@NoArgsConstructor @AllArgsConstructor
@DynamicUpdate
public class BillingInfo extends BaseEntity {

    @Id @GeneratedValue
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


    // 처음 insert 시 userStatus 업데이트를 위한 코드
    @PrePersist
    public void prePersist() {
        if (this.getBillingInfoStatus() == null) {
            this.billingInfoStatus = BillingInfoStatus.ACTIVE;
        }
    }

    public Optional<BillingInfo> isActiveBillingInfo(CardName cardName) {
        if ((this.billingInfoStatus == BillingInfoStatus.ACTIVE) && (this.cardName == cardName)){
            return Optional.of(this);
        }
        return Optional.empty();
    }

    public void delete() {
        this.paymentKey = null;
        this.billingInfoStatus = BillingInfoStatus.INACTIVE;
    }
}
