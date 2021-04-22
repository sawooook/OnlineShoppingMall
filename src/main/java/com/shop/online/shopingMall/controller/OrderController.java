package com.shop.online.shopingMall.controller;

import com.shop.online.shopingMall.concern.ResponseMessage;
import com.shop.online.shopingMall.concern.ResponseStatus;
import com.shop.online.shopingMall.dto.order.OrderRequestDto;
import com.shop.online.shopingMall.dto.order.OrderResponseDto;
import com.shop.online.shopingMall.service.OrderService;
import com.shop.online.shopingMall.service.SecurityService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;
    private final SecurityService securityService;

    public OrderController(OrderService orderService, SecurityService securityService) {
        this.orderService = orderService;
        this.securityService = securityService;
    }


    /**
     * 제품 주문 관련 API
     *
     * 성공시 return OK, Order 정보
     * 실패시 return BadRequest
     *
     * */
    @PostMapping
    public ResponseEntity makeOrder(@RequestBody OrderRequestDto orderRequestDto) {
        orderService.readyToOrder(orderRequestDto);
        return ResponseEntity.ok().body(new ResponseMessage(ResponseStatus.OK, null));
    }

    @GetMapping("/{id}")
    public ResponseEntity cancelOrder(@PathVariable Long id) {
        orderService.cancel(id);
        return ResponseEntity.ok().body(new ResponseMessage(ResponseStatus.OK, null));
    }

    @GetMapping("/list")
    public ResponseEntity listOrder() {
        Long userId = securityService.findUserIdbyToken();
        List<OrderResponseDto> orderList = orderService.getOrderList(userId);
        return ResponseEntity.ok().body(new ResponseMessage(ResponseStatus.OK, orderList));
    }
}
