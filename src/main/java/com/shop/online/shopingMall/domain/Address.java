package com.shop.online.shopingMall.domain;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;

@Getter
@Embeddable
@NoArgsConstructor
public class Address {

    @Column(name = "address_code")
    private String addressCode;

    @Column(name = "address_detail")
    private String addressDetail;

    public Address(String addressCode, String addressDetail) {
        this.addressCode = addressCode;
        this.addressDetail = addressDetail;
    }
}
