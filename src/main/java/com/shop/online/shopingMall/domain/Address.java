package com.shop.online.shopingMall.domain;

import lombok.*;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;

@Embeddable @Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Address {
    private String addressCode;
    private String addressDetail;
}
