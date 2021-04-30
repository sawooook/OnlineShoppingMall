package com.shop.online.shopingMall.service;

import com.shop.online.shopingMall.domain.Delivery;
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
        return checkDeliveryStatus(delivery);
    }

    private String checkDeliveryStatus(Delivery delivery) {
        if (delivery.isDeliveryGoing()) {
            return "배송중입니다";
        } else if (delivery.isDeliveryCancel()) {
            return "취소된 배송입니다.";
        } else if (delivery.isDeliveryReady()) {
            return "배송 준비단계에 있습니다.";
        } else {
            return "배송 완료되었습니다.";
        }
    }
}
