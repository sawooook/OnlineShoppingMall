package com.shop.online.shopingMall.dto.order;

import com.querydsl.core.annotations.QueryProjection;
import com.shop.online.shopingMall.domain.Order;
import com.shop.online.shopingMall.domain.OrderItem;
import com.shop.online.shopingMall.domain.ProductOption;
import com.shop.online.shopingMall.domain.enumType.OrderStatus;
import com.shop.online.shopingMall.dto.product.ProductOptionDto;
import com.shop.online.shopingMall.dto.product.ProductPriceDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class OrderResponseDto {
    private Long id;
    private String name;
    private OrderStatus orderStatus;
    private int price;
    private List<ProductOptionDto> options;

    public OrderResponseDto(Order order) {
        this.id = order.getId();
        this.name = order.getName();
        this.orderStatus = order.getOrderStatus();
        this.price = order.getProduct().lastRegisterPrice();
        this.options = order.getOrderItems()
                .stream()
                .map(orderItem -> new ProductOptionDto(orderItem.getColor(), orderItem.getSize()))
                .collect(Collectors.toList());
    }
}
