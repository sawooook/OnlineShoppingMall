package com.shop.online.shopingMall.dto.order;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class AmountDto {
    @JsonProperty(value = "amount")
    private int total;

    public AmountDto(int total) {
        this.total = total;
    }
}
