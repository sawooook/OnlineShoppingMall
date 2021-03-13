package com.shop.online.shopingMall.dto.user;

import com.shop.online.shopingMall.domain.Address;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @Builder
@NoArgsConstructor @AllArgsConstructor
public class AddressDto {
    private String addressCode;
    private String addressDetail;

    public static Address toEntity(Address address) {
        return Address.builder()
                .addressDetail(address.getAddressDetail()).addressCode(address.getAddressCode()).build();
    }

    public static AddressDto toDto(Address address) {
        return AddressDto.builder()
                .addressDetail(address.getAddressDetail()).addressCode(address.getAddressCode()).build();
    }
}
