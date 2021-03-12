package com.shop.online.shopingMall.domain;

import com.shop.online.shopingMall.domain.base.BaseEntity;
import com.shop.online.shopingMall.domain.enumType.DeliveryStatus;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter @Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Delivery extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "delivery_id")
    private Long id;

    @Embedded
    private Address address;

    @Enumerated(EnumType.STRING)
    private DeliveryStatus deliveryStatus;

    @OneToOne(mappedBy = "delivery")
    private Order order;

    /**
     * 배송상태를 체크
    * */
    public boolean isDeliveryReady() {
        return (getDeliveryStatus() == DeliveryStatus.ing);
    }

    /**
     * 배송 상태를 취소로변경
    * */
    public void cancel() {
        this.deliveryStatus = DeliveryStatus.cancel;
    }
}
