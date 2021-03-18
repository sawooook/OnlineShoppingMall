package com.shop.online.shopingMall.repository;

import com.shop.online.shopingMall.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long>, ProductRepositoryCustom {
}
