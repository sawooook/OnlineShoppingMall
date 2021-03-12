package com.shop.online.shopingMall.service;

import com.shop.online.shopingMall.domain.Payment;
import com.shop.online.shopingMall.dto.util.KakaoPayApproveResponseDto;
import com.shop.online.shopingMall.repository.PaymentRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final DeliveryService deliveryService;

    /*
    * 결제 성공이 완료시 배달테이블에 추가한다.
    * */
    public void charge(KakaoPayApproveResponseDto responseDto) {
        Payment payment = KakaoPayApproveResponseDto.toEntity(responseDto);
        paymentRepository.save(payment);
        deliveryService.readyToDelivery(payment.getOrder());
    }
}
