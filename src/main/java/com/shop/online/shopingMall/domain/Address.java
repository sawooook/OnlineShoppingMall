package com.shop.online.shopingMall.domain;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;

@Embeddable
public class Address {

    private String zipCode;
    private String street;
    private String city;
}
