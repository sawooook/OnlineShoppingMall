package com.shop.online.shopingMall.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.shop.online.shopingMall.domain.BillingInfo;
import com.shop.online.shopingMall.domain.QBillingInfo;
import com.shop.online.shopingMall.domain.QUser;
import com.shop.online.shopingMall.domain.User;
import com.shop.online.shopingMall.domain.enumType.BillingInfoStatus;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class BillingInfoRepositoryImpl extends QuerydslRepositorySupport implements BillingInfoRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public BillingInfoRepositoryImpl(JPAQueryFactory queryFactory) {
        super(BillingInfo.class);
        this.queryFactory = queryFactory;
    }

    @Override
    public Optional<BillingInfo> activeBillingInfo(User user) {

        return Optional.ofNullable(queryFactory
                .selectFrom(QBillingInfo.billingInfo)
                .where(
                        QBillingInfo.billingInfo.user.eq(user),
                        QBillingInfo.billingInfo.billingInfoStatus.eq(BillingInfoStatus.ACTIVE),
                        QBillingInfo.billingInfo.uniqueNumber.isNotNull())
                .fetchFirst());
    }
}
