package com.shop.online.shopingMall.service;

import com.shop.online.shopingMall.domain.Address;
import com.shop.online.shopingMall.domain.Delivery;
import com.shop.online.shopingMall.domain.Order;
import com.shop.online.shopingMall.domain.enumType.DeliveryStatus;
import com.shop.online.shopingMall.repository.DeliveryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@AllArgsConstructor
public class DeliveryService {

    private final DeliveryRepository deliveryRepository;

    public void readyToDelivery(Order order) {
        Address userAddress = order.getUser().getAddress();
        Delivery delivery = Delivery.builder().deliveryStatus(DeliveryStatus.ready)
                .address(new Address(userAddress.getAddressCode(), userAddress.getAddressDetail())).build();
        deliveryRepository.save(delivery);
        order.updateOrderStatus();
    }
}
