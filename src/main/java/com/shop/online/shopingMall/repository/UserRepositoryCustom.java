package com.shop.online.shopingMall.repository;

import com.shop.online.shopingMall.domain.User;

import java.util.List;
import java.util.Optional;

public interface UserRepositoryCustom {
    List<String> pushOnUser();
    Optional<User> findUserAndActiveBillingInfo(Long user);
}
