//package com.shop.online.shopingMall.repository;
//
//import com.shop.online.shopingMall.domain.BillingInfo;
//import com.shop.online.shopingMall.domain.User;
//import com.shop.online.shopingMall.domain.enumType.CardName;
//import com.shop.online.shopingMall.exception.NotFoundBillingInfoException;
//import com.shop.online.shopingMall.exception.NotFoundUserException;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.transaction.annotation.Transactional;
//
//import javax.persistence.EntityManager;
//
//import static org.assertj.core.api.Assertions.*;
//import static org.junit.jupiter.api.Assertions.*;
//
//@SpringBootTest
//@Transactional
//class BillingInfoRepositoryImplTest {
//
//    @Autowired
//    private BillingInfoRepository billingInfoRepository;
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Autowired
//    private EntityManager entityManager;
//    @BeforeEach
//    void beforeEach() {
//        User user1 = User.builder().name("test1").pushToken("test1").build();
//        User user2 = User.builder().name("test2").pushToken("test2").build();
//        entityManager.persist(user1);
//        entityManager.persist(user2);
//
//        BillingInfo billingInfo1 = BillingInfo.builder().uniqueNumber("test1").user(user1).paymentKey("test1").cardName(CardName.kakao).build();
//        BillingInfo billingInfo2 = BillingInfo.builder().uniqueNumber("test2").user(user1).paymentKey("test2").cardName(CardName.kakao).build();
//        entityManager.persist(billingInfo1);
//        entityManager.persist(billingInfo2);
//    }
//
//    @Test
//    public void 활성화된_카드_가져오기() {
//        User user = userRepository.findById(1L).orElseThrow(NotFoundUserException::new);
//        BillingInfo billingInfo = billingInfoRepository.activeBillingInfo(user).orElseThrow(NotFoundBillingInfoException::new);
//        assertThat(billingInfo.getPaymentKey()).isEqualTo("test1");
//    }
//
//}