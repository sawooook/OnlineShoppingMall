package com.shop.online.shopingMall.controller;

import com.shop.online.shopingMall.service.DeliveryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import util.ApiResponse;

import static util.ApiResponse.success;

@RestController("/delivery")
@RequiredArgsConstructor
public class DeliveryController {

    private final DeliveryService deliveryService;


    @GetMapping("/{id}")
    public ApiResponse<String> checkDelivery(@PathVariable Long id) {
        String message = deliveryService.checkDelivery(id);
        return success(message);
    }
}
