package com.shop.online.shopingMall.dto.order;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.shop.online.shopingMall.domain.Order;
import com.shop.online.shopingMall.domain.Payment;
import lombok.*;

import java.time.LocalDateTime;

/**
* 결제에대한 응답값으로 받으려는 데이터 형태
 * 공통 DTO
 *
* */


@Data @Builder
@NoArgsConstructor @AllArgsConstructor
public class OrderResultResponseDto {
    private String aid;
    private String tid;
    private String cid;
    private String sid;
    private Amount amount;
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
    private static class Amount {
        @JsonProperty(value = "amount")
        private int total;
    }

    public static Payment toEntity(OrderResultResponseDto responseDto) {
        return Payment.builder().aid(responseDto.getAid()).tid(responseDto.getTid()).order(responseDto.getOrder())
                .cid(responseDto.getCid()).amount(String.valueOf(responseDto.getAmount()))
                .itemName(responseDto.getItemName()).quantity(responseDto.getQuantity())
                .createdAt(LocalDateTime.parse(responseDto.getCreatedAt()))
                .approvedAt(LocalDateTime.parse(responseDto.getApprovedAt())).build();
    }
}
