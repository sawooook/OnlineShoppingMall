package com.shop.online.shopingMall.dto.util;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.shop.online.shopingMall.domain.Payment;
import com.shop.online.shopingMall.domain.enumType.CardName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor @AllArgsConstructor
public class KakaoPayApproveResponseDto {
    private String aid;
    private String tid;
    private String cid;
    private String sid;
    private String item_name;
    private String quantity;
    @JsonProperty("approved_at")
    private LocalDateTime approvedAt;
    @JsonProperty("created_at")
    private LocalDateTime createdAt;

}
