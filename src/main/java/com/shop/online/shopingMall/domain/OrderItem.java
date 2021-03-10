package com.shop.online.shopingMall.domain;

import com.shop.online.shopingMall.domain.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity @Getter
@Builder
@NoArgsConstructor @AllArgsConstructor
public class OrderItem extends BaseEntity {

    @Id @GeneratedValue
    @Column(name = "order_item_id")
    private Long id;
    private String name;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    private int price;

    private String size;

    private String color;

    public void setOrder(Order order) {
        this.order = order;
    }
}
