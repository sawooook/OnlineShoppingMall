package com.shop.online.shopingMall.domain;

import lombok.Builder;
import lombok.RequiredArgsConstructor;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;

@Embeddable
@Builder
public class Address {

    private String addressCode;
    private String addressDetail;
}
