package com.shop.online.shopingMall.repository;

import com.shop.online.shopingMall.domain.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CartRepository extends CrudRepository<Cart, String> {

}
