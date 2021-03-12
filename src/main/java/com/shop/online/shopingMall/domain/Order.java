package com.shop.online.shopingMall.domain;

import com.shop.online.shopingMall.domain.base.BaseEntity;
import com.shop.online.shopingMall.domain.enumType.OrderStatus;
import com.shop.online.shopingMall.dto.OrderItemDto;
import com.shop.online.shopingMall.exception.NotFoundBillingInfoException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.annotation.PostConstruct;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor @AllArgsConstructor
@Entity @Builder @Getter
@Table(name = "orders")
public class Order extends BaseEntity {

    @Id @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "billingInfo_id")
    private BillingInfo billingInfo;

    @OneToOne
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems = new ArrayList<>();

    @OneToMany(mappedBy = "order")
    private List<Payment> payments = new ArrayList<>();

    private Address address;


    /**
    *  저장 성공시 order 상태를 ready로 변경함
    * */
    public void updateOrderStatus() {
        this.orderStatus = OrderStatus.ready;
    }

    /**
     * 주문 생성 메소드
    * */
    public static Order createOrder(User user, Product product, List<OrderItem> orderItems) {
        BillingInfo billingInfo = user.activeBillingInfo().orElseThrow(NotFoundBillingInfoException::new);
        Order order = Order.builder()
                .billingInfo(billingInfo).user(user).product(product).build();
        for (OrderItem orderItem : orderItems) {
            System.out.println("orderItem.getColor() =========================================== " + orderItem.getColor());
            order.addOrderItem(orderItem);
        }
        return order;
    }

    private void addOrderItem(OrderItem orderItem) {
        System.out.println("orderItem = " + orderItem.getColor());
        System.out.println("orderItem = " + orderItem.getName());
        System.out.println("orderItem = " + orderItem.getPrice());
        System.out.println("orderItem = " + orderItem.getId());
        System.out.println("orderItems = " + orderItems);

        orderItems.add(orderItem);
        orderItem.setOrder(this);
    }

    public boolean cancel() {
        if (delivery.isDeliveryReady()) {
            this.orderStatus = OrderStatus.cancel;
            delivery.cancel();
            return true;
        } else {
            return false;
        }

    }
}
