package com.shop.online.shopingMall.dto.util;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.shop.online.shopingMall.domain.Payment;
import com.shop.online.shopingMall.domain.enumType.CardName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data @Builder
@NoArgsConstructor @AllArgsConstructor
public class KakaoPayApproveResponseDto {
    private String aid;
    private String tid;
    private String cid;
    private String sid;
//    private String amount;
//    private String cardInfo;
    private String item_name;
    private String quantity;
    @JsonProperty("approved_at")
    private LocalDateTime approvedAt;
    @JsonProperty("created_at")
    private LocalDateTime createdAt;

    public static Payment toEntity(KakaoPayApproveResponseDto responseDto) {
        return Payment.builder().tid(responseDto.getTid()).aid(responseDto.getAid()).cid(responseDto.getCid())
                .itemName(responseDto.getItem_name()).quantity(responseDto.getQuantity())
                .cardName(CardName.kakao).approvedAt(responseDto.getApprovedAt()).createdAt(responseDto.getCreatedAt()).build();
    }
}
