package com.shop.online.shopingMall.controller;

import com.shop.online.shopingMall.concern.ResponseMessage;
import com.shop.online.shopingMall.concern.ResponseStatus;
import com.shop.online.shopingMall.dto.OrderRequestDto;
import com.shop.online.shopingMall.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity makeOrder(@RequestBody OrderRequestDto orderRequestDto) {

        orderService.createOrder(orderRequestDto);

        return ResponseEntity.ok().body(new ResponseMessage(ResponseStatus.OK,"주문 성공", null));

    }
}
