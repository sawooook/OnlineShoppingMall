package com.shop.online.shopingMall.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @Builder
@NoArgsConstructor @AllArgsConstructor
public class AddressDto {
    private String addressCode;
    private String addressDetail;
}
