package com.shop.online.shopingMall.repository;

import com.shop.online.shopingMall.domain.Product;
import com.shop.online.shopingMall.domain.enumType.ProductStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long>, ProductRepositoryCustom {
    Optional<Product> findByIdAndProductStatus(Long id, ProductStatus status);
}
