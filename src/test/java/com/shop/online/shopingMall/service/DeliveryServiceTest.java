package com.shop.online.shopingMall.service;

import com.shop.online.shopingMall.domain.Delivery;
import com.shop.online.shopingMall.domain.Order;
import com.shop.online.shopingMall.domain.enumType.DeliveryStatus;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@Transactional
@Commit
class DeliveryServiceTest {

    @Autowired
    private DeliveryService deliveryService;

    @Autowired
    EntityManager entityManager;
    @BeforeEach
    void settings() {
        Delivery delivery1 = Delivery.builder().deliveryStatus(DeliveryStatus.success).build();
        Delivery delivery2 = Delivery.builder().deliveryStatus(DeliveryStatus.ing).build();

        deliveryService.save(delivery1);
        deliveryService.save(delivery2);
    }


    @Test
    public void 배달상태_체크_배송완료상태() {
        String message = deliveryService.checkDelivery(1L);
        Assertions.assertThat(message).isEqualTo("배송완료된 제품입니다");
    }

    @Test
    public void 배달상태_체크_배송중인상태() {
        String message = deliveryService.checkDelivery(2L);
        Assertions.assertThat(message).isEqualTo("배송중입니다");
    }

}