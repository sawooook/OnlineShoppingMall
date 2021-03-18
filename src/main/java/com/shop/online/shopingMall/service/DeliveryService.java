package com.shop.online.shopingMall.service;

import com.shop.online.shopingMall.domain.Address;
import com.shop.online.shopingMall.domain.Delivery;
import com.shop.online.shopingMall.domain.Order;
import com.shop.online.shopingMall.domain.enumType.DeliveryStatus;
import com.shop.online.shopingMall.exception.NotFoundDeliveryException;
import com.shop.online.shopingMall.repository.DeliveryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@AllArgsConstructor
public class DeliveryService {

    private final DeliveryRepository deliveryRepository;

    public void save(Delivery delivery) {
        deliveryRepository.save(delivery);
    }

    public String checkDelivery(Long id) {
        Delivery delivery = deliveryRepository.findById(id).orElseThrow(NotFoundDeliveryException::new);
        if (isDeliverying(delivery)) {
            return "배송중입니다";
        } else {

            return "배송완료된 제품입니다";
        }
    }

    public boolean isDeliverying(Delivery delivery) {
        return delivery.getDeliveryStatus() == DeliveryStatus.ing;
    }
}
