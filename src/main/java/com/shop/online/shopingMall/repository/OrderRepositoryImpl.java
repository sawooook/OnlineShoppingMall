package com.shop.online.shopingMall.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.shop.online.shopingMall.domain.Order;
import com.shop.online.shopingMall.domain.enumType.OrderStatus;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

import static com.shop.online.shopingMall.domain.QOrder.*;

public class OrderRepositoryImpl extends QuerydslRepositorySupport implements OrderRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    public OrderRepositoryImpl(JPAQueryFactory jpaQueryFactory) {
        super(Order.class);
        this.jpaQueryFactory = jpaQueryFactory;
    }

    @Override
    public List<Order> findOrderList(Long userId) {
        return jpaQueryFactory
                .selectFrom(order)
                .join(order.user, order.user)
                .where(order.user.id.eq(userId),
                        order.orderStatus.eq(OrderStatus.success),
                        order.orderStatus.eq(OrderStatus.cancel)
                )
                .fetch();
    }
}
