package com.shop.online.shopingMall.dto.order;

import com.shop.online.shopingMall.domain.OrderItem;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Builder @Data
@NoArgsConstructor @AllArgsConstructor
public class OrderItemDto {
    private String size;
    private String color;
    private int price;

    public static List<OrderItem> toEntity(List<OrderItemDto> orderItemDto) {
        List<OrderItem> orderItems = new ArrayList<>();

        for (OrderItemDto orderItem : orderItemDto) {
            OrderItem item = OrderItem.builder()
                    .color(orderItem.getColor()).size(orderItem.getSize()).price(orderItem.getPrice()).build();
            orderItems.add(item);
        }
        return orderItems;
    }
}
