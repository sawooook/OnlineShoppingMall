package com.shop.online.shopingMall.dto.order;

import com.shop.online.shopingMall.domain.Order;
import com.shop.online.shopingMall.domain.OrderItem;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data @Builder
@AllArgsConstructor @NoArgsConstructor
public class OrderResponseDto {
    private List<OrderDto> orders;


    public static List<OrderResponseDto> toDto(List<Order> orderList) {

        List<OrderDto> orderItemList = new ArrayList<>();
        List<OrderResponseDto> responseDtoList = new ArrayList<>();

        for (Order order : orderList) {
            for (OrderItem orderItem : order.getOrderItems()) {

                OrderDto orderDto = OrderDto.builder().color(orderItem.getColor()).name(orderItem.getOrder().getName())
                        .price(orderItem.getPrice()).size(orderItem.getSize()).orderStatus(order.getOrderStatus()).build();

                orderItemList.add(orderDto);
            }

            OrderResponseDto orderResponse = OrderResponseDto.builder().orders(orderItemList).build();
            responseDtoList.add(orderResponse);
        }
        return responseDtoList;
    }
}
