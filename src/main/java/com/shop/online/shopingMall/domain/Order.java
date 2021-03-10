package com.shop.online.shopingMall.domain;

import com.shop.online.shopingMall.domain.base.BaseEntity;
import com.shop.online.shopingMall.domain.enumType.OrderStatus;
import com.shop.online.shopingMall.dto.OrderItemDto;
import com.shop.online.shopingMall.exception.NotFoundBillingInfoException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.annotation.PostConstruct;
import javax.persistence.*;
import java.util.List;

@NoArgsConstructor @AllArgsConstructor
@Entity @Builder
@Table(name = "orders")
public class Order extends BaseEntity {

    @Id @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "billingInfo_id")
    private BillingInfo billingInfo;

    @OneToOne
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems;

    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;

    /**
    *  저장에 성공하면 order의 상태를 success로 변경함
    * */
    @PostPersist
    public void orderStatusConstruct() {
        this.orderStatus = OrderStatus.success;
    }

    /**
     * 주문 생성 메소드
    * */
    public static Order createOrder(User user, Product product, List<OrderItem> orderItems) {
        BillingInfo billingInfo = user.activeBillingInfo().orElseThrow(NotFoundBillingInfoException::new);
        Order order = Order.builder()
                .billingInfo(billingInfo).user(user).product(product).build();
        for (OrderItem orderItem : orderItems) {
            order.addOrderItem(orderItem);
        }
        return order;
    }

    private void addOrderItem(OrderItem orderItem) {
        orderItems.add(orderItem);
        orderItem.setOrder(this);
    }
}
