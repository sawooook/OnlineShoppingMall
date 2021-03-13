package com.shop.online.shopingMall.dto.util;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.shop.online.shopingMall.domain.Order;
import com.shop.online.shopingMall.domain.Payment;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
* 받으려는 데이터 형태
 * 카카오페이에서 제공하는 예시 데이터
 * {
 *  "aid": "A5678901234567890123",
 *  "cid": "TCSUBSCRIP",
 *  "sid": "S1234567890987654321",
 *  "tid": "T2468013579246801357",
 *  "partner_order_id": "subscription_order_id_1",
 *  "partner_user_id": "subscription_user_id_1",
 *  "payment_method_type": "MONEY",
 *  "item_name": "음악정기결제",
 *  "quantity": 1,
 *  "amount": {
 *   "total": 9900,
 *   "tax_free": 0,
 *   "vat": 900
 *  },
 *  "created_at": "2016-11-17T16:02:19",
 *  "approved_at": "2016-11-17T16:02:20"
 * }
* */

@Data @Builder
@NoArgsConstructor @AllArgsConstructor
public class KakaoPayChargeResponseDto {
    private String aid;
    private String tid;
    private String cid;
    private String sid;
    private KakaoChargeAmountResponse amount;
    @JsonProperty("partner_order_id")
    private String partnerOrderId;
    private String itemName;
    private String quantity;
    @JsonProperty("created_at")
    private String createdAt;
    @JsonProperty("approved_at")
    private String approvedAt;
    private Order order;

    // amount 안에있는 내용을 파싱받기 위해서 제작
    @Getter @Setter
    private static class KakaoChargeAmountResponse {
        @JsonProperty(value = "amount")
        private int total;
    }

    public static Payment toEntity(KakaoPayChargeResponseDto responseDto) {
        return Payment.builder().aid(responseDto.getAid()).tid(responseDto.getTid()).order(responseDto.getOrder())
                .cid(responseDto.getCid()).amount(String.valueOf(responseDto.getAmount()))
                .itemName(responseDto.getItemName()).quantity(responseDto.getQuantity())
                .createdAt(LocalDateTime.parse(responseDto.getCreatedAt()))
                .approvedAt(LocalDateTime.parse(responseDto.getApprovedAt())).build();
    }
}
