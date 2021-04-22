package com.shop.online.shopingMall.domain;

import com.shop.online.shopingMall.domain.base.BaseEntity;
import com.shop.online.shopingMall.domain.enumType.OrderStatus;
import com.shop.online.shopingMall.exception.NotFoundBillingInfoException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity @Getter
@NoArgsConstructor @AllArgsConstructor
@Table(name = "orders")
public class Order extends BaseEntity {

    @Id
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

    private int totalAmount;

    @Builder
    public Order(Long id, int totalAmount ,OrderStatus orderStatus, String name, BillingInfo billingInfo, Delivery delivery, User user, Product product, Address address) {
        this.id = id;
        this.orderStatus = orderStatus;
        this.name = name;
        this.billingInfo = billingInfo;
        this.delivery = delivery;
        this.user = user;
        this.product = product;
        this.address = address;
        this.totalAmount = totalAmount;
    }

    public Order(User user, Product product, List<OrderItem> items, Address address) {
        this.orderStatus = OrderStatus.ready;
        this.user = user;
        this.name = product.getName();
        this.product = product;
        this.address = address;
        this.totalAmount = items.stream().mapToInt(OrderItem::getPrice).sum();
        this.delivery = null;
    }


    /**
    *  저장 성공시 order 상태를 ready로 변경함
    * */
    public void updateOrderStatus() {
        this.orderStatus = OrderStatus.ready;
    }

    /**
     * 주문 생성 메소드
    * */
    public static Order createOrder(User user, Product product, List<OrderItem> orderItemList, Address address, BillingInfo billingInfo) {
        int totalAmount = 0;

        Order order = Order.builder().name(product.getName()).address(address)
                .billingInfo(billingInfo).user(user).product(product).orderStatus(OrderStatus.ready).build();

        for (OrderItem orderItem : orderItemList) {
            totalAmount += orderItem.getPrice();
            order.addOrderItem(orderItem);
        }

        order.updateTotalAmount(totalAmount);

        return order;
    }

    private void updateTotalAmount(int totalAmount) {
        this.totalAmount = totalAmount;
    }

    private void addOrderItem(OrderItem orderItem) {
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

    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
    }
}
