package com.shop.online.shopingMall.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder @Data
@AllArgsConstructor @NoArgsConstructor
public class OrderRequestDto {
    private int userId;
    private int productId;
    private List<OrderItemDto> itemList;
}
