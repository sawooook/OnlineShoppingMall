package com.shop.online.shopingMall.domain;

import com.shop.online.shopingMall.domain.base.BaseEntity;
import com.shop.online.shopingMall.domain.enumType.DeliveryStatus;
import lombok.*;
import org.aspectj.weaver.ast.Or;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Delivery extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "delivery_id")
    private Long id;

    @Embedded
    private Address address;

    @Enumerated(EnumType.STRING)
    @Column(name = "delivery_status")
    private DeliveryStatus deliveryStatus;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    public Delivery(Address address, Order order) {
        this.address = address;
        this.deliveryStatus = DeliveryStatus.ready;
        this.order = order;
    }

    /**
     * 배송상태를 체크
    * */
    public boolean isDeliveryGoing() {
        return (getDeliveryStatus() == DeliveryStatus.ing);
    }

    public boolean isDeliverySuccess() {
        return (getDeliveryStatus() == DeliveryStatus.success);
    }

    public boolean isDeliveryCancel() {
        return (getDeliveryStatus() == DeliveryStatus.cancel);
    }

    public boolean isDeliveryReady() {
        return (getDeliveryStatus() == DeliveryStatus.ready);
    }



    /**
     * 배송 상태를 취소로변경
    * */
    public void cancel() {
        this.deliveryStatus = DeliveryStatus.cancel;
    }
}
