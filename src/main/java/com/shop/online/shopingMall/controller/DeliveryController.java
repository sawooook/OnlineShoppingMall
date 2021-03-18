package com.shop.online.shopingMall.controller;

import com.shop.online.shopingMall.concern.ResponseMessage;
import com.shop.online.shopingMall.concern.ResponseStatus;
import com.shop.online.shopingMall.service.DeliveryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController("/delivery")
@RequiredArgsConstructor
public class DeliveryController {

    private final DeliveryService deliveryService;


    @GetMapping("/{id}")
    public ResponseEntity checkDelivery(@PathVariable Long id) {
        String message = deliveryService.checkDelivery(id);
        return ResponseEntity.ok().body(new ResponseMessage(ResponseStatus.OK, message, null));
    }
}
