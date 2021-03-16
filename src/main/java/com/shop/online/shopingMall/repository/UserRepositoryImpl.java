package com.shop.online.shopingMall.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.shop.online.shopingMall.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;


public class UserRepositoryImpl extends QuerydslRepositorySupport implements UserRepositoryCustom {


    public UserRepositoryImpl(Class<?> domainClass) {
        super(domainClass);
    }

    @Override
    public void pushOnUser(User user) {
    }

    @Override
    public void save(User user) {

    }
}
