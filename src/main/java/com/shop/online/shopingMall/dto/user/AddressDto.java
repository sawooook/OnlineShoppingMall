package com.shop.online.shopingMall.dto.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.shop.online.shopingMall.domain.Address;
import com.shop.online.shopingMall.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AddressDto {

    @JsonProperty("address_code")
    private String addressCode;

    @JsonProperty("address_detail")
    private String addressDetail;

    public AddressDto(User user) {
        this.addressCode = user.getAddress().getAddressCode();
        this.addressDetail = user.getAddress().getAddressDetail();
    }
}
