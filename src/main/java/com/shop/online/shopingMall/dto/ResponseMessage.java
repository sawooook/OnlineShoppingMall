package com.shop.online.shopingMall.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor @NoArgsConstructor
public class ResponseMessage {
    private ResponseStatus responseStatus;
    private String message;
    private Object data;
}
