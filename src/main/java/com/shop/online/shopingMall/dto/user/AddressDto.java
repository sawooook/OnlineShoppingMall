package com.shop.online.shopingMall.dto.user;

import com.shop.online.shopingMall.domain.Address;
import com.shop.online.shopingMall.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @Builder
@NoArgsConstructor @AllArgsConstructor
public class AddressDto {
    private String addressCode;
    private String addressDetail;

    public AddressDto(User user) {
        this.addressCode = user.getAddress().getAddressCode();
        this.addressDetail = user.getAddress().getAddressDetail();
    }
    
    public static AddressDto toDto(Address address) {
        return AddressDto.builder()
                .addressDetail(address.getAddressDetail()).addressCode(address.getAddressCode()).build();
    }
}
