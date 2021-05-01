package com.shop.online.shopingMall.dto.util;

import com.shop.online.shopingMall.domain.enumType.CardName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor @AllArgsConstructor
public class KakaoPayApproveAmountResponseDto {
    private String itemName;
    private String quantity;
    private LocalDateTime approvedAt;
    @Enumerated(EnumType.STRING)
    private CardName cardName;
}
