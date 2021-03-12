package com.shop.online.shopingMall.controller;

import com.shop.online.shopingMall.concern.ResponseMessage;
import com.shop.online.shopingMall.concern.ResponseStatus;
import com.shop.online.shopingMall.dto.OrderRequestDto;
import com.shop.online.shopingMall.dto.order.OrderResponseDto;
import com.shop.online.shopingMall.service.OrderService;
import com.shop.online.shopingMall.service.SecurityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;
    private final SecurityService securityService;

    @PostMapping
    public ResponseEntity makeOrder(@RequestBody OrderRequestDto orderRequestDto) {
        orderService.readyToOrder(orderRequestDto);
        return ResponseEntity.ok().body(new ResponseMessage(ResponseStatus.OK,"주문 성공", null));
    }

    @GetMapping("/{id}")
    public ResponseEntity cancelOrder(@PathVariable Long id) {
        orderService.cancel(id);
        return ResponseEntity.ok().body(new ResponseMessage(ResponseStatus.OK, "주문 취소 완료", null));
    }

    @GetMapping("/list")
    public ResponseEntity listOrder() {
        Long userId = securityService.findUserIdbyToken();
        List<OrderResponseDto> orderList = orderService.getOrderList(userId);
        return ResponseEntity.ok().body(new ResponseMessage(ResponseStatus.OK, "주문 내역", orderList));
    }
}
