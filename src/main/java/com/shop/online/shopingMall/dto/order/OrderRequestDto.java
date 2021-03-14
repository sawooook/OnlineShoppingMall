package com.shop.online.shopingMall.dto.order;


import com.shop.online.shopingMall.dto.order.OrderItemDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder @Data
@AllArgsConstructor @NoArgsConstructor
public class OrderRequestDto {
    private int userId;
    private String name;
    private int productId;
    private String addressCode;
    private String addressDetail;
    private List<OrderItemDto> itemList;
}