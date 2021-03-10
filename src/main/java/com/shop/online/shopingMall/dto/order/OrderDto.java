package com.shop.online.shopingMall.dto.order;

import com.shop.online.shopingMall.domain.enumType.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor @AllArgsConstructor
public class OrderDto {
    private Long id;
    private String name;
    private int price;
    private String size;
    private String color;
    private OrderStatus orderStatus;
}
