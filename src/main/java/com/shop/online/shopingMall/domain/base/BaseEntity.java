package com.shop.online.shopingMall.domain.base;

import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass
public abstract class BaseEntity {
    private LocalDateTime created_at;
    private LocalDateTime updated_at;
}
