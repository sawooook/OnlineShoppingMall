package com.shop.online.shopingMall.repository;

import com.shop.online.shopingMall.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long>, UserRepositoryCustom {
    boolean existsByEmail(String email);
    Optional<User> findByEmail(String email);
    Optional<User> findUserAndActiveBillingInfo(Long userId);
}
