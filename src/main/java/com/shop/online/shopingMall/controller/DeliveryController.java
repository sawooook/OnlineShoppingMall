package com.shop.online.shopingMall.controller;

import com.shop.online.shopingMall.service.DeliveryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.shop.online.shopingMall.util.ApiResponse;

import static com.shop.online.shopingMall.util.ApiResponse.success;

@RestController("/delivery")
@RequiredArgsConstructor
public class DeliveryController {

    private final DeliveryService deliveryService;

    /**
    * 배송의 상태를 체크할 수 있는 API
    * */
    @GetMapping("/{id}")
    public ApiResponse<String> checkDelivery(@PathVariable Long id) {
        String message = deliveryService.checkDelivery(id);
        return success(message);
    }
}
