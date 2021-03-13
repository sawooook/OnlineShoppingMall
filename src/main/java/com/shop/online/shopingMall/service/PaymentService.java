package com.shop.online.shopingMall.service;

import com.shop.online.shopingMall.domain.Order;
import com.shop.online.shopingMall.domain.Payment;
import com.shop.online.shopingMall.dto.util.KakaoPayApproveResponseDto;
import com.shop.online.shopingMall.dto.util.KakaoPayChargeResponseDto;
import com.shop.online.shopingMall.repository.PaymentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final DeliveryService deliveryService;
    private final OrderService orderService;

    /*
    * 결제 성공이 완료시 배달테이블에 추가한다.
    * */
    public void charge(KakaoPayChargeResponseDto responseDto) {
        Order order = orderService.findOrder(Long.valueOf(responseDto.getPartnerOrderId()));
        responseDto.setOrder(order);
        Payment payment = KakaoPayChargeResponseDto.toEntity(responseDto);
        paymentRepository.save(payment);

        System.out.println(payment.getOrder().getId());
        deliveryService.readyToDelivery(payment.getOrder());
    }
}
