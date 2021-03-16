package com.shop.online.shopingMall.repository;

import com.shop.online.shopingMall.domain.BillingInfo;
import com.shop.online.shopingMall.domain.User;

import java.util.Optional;

public interface BillingInfoRepositoryCustom {

    Optional<BillingInfo> activeBillingInfo(User user);

}
