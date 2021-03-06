package com.shop.online.shopingMall.dto.util;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor @AllArgsConstructor
public class KakaoPayResultResponseDto {
    private String amount;
    private String cardInfo;
    private String itemName;
    private String quantity;
    private LocalDateTime approveAt;
}
