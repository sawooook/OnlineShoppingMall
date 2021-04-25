package com.shop.online.shopingMall.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.shop.online.shopingMall.domain.User;
import com.shop.online.shopingMall.domain.enumType.BillingInfoStatus;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static com.shop.online.shopingMall.domain.QBillingInfo.*;
import static com.shop.online.shopingMall.domain.QUser.*;


@Repository
public class UserRepositoryImpl extends QuerydslRepositorySupport implements UserRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    public UserRepositoryImpl(JPAQueryFactory jpaQueryFactory) {
        super(User.class);
        this.jpaQueryFactory = jpaQueryFactory;
    }


    @Override
    public List<String> pushOnUser() {
        return jpaQueryFactory
                .select(user.pushToken)
                .from(user)
                .where(user.pushToken.isNotNull())
                .fetch();
    }

    // 유저정보가 있는지 확인, 결제키가 있고 삭제되지 않은 결제카드인지
    @Override
    public Optional<User> findUserAndActiveBillingInfo(Long userId) {
        return Optional.ofNullable(jpaQueryFactory.selectFrom(user)
                .join(user.billingInfoList, billingInfo)
                .on(user.id.eq(userId), billingInfo.paymentKey.isNotNull(), billingInfo.billingInfoStatus.eq(BillingInfoStatus.ACTIVE))
                .fetchOne());
    }
}
