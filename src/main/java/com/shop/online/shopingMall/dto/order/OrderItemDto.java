package com.shop.online.shopingMall.dto.order;

import com.shop.online.shopingMall.domain.OrderItem;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class OrderItemDto {
    private String size;
    private String color;
    private int price;
}
