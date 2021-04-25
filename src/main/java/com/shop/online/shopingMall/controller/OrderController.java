package com.shop.online.shopingMall.controller;

import com.shop.online.shopingMall.dto.order.OrderRequestDto;
import com.shop.online.shopingMall.dto.order.OrderResponseDto;
import com.shop.online.shopingMall.service.OrderService;
import com.shop.online.shopingMall.service.SecurityService;
import org.springframework.web.bind.annotation.*;
import util.ApiResponse;

import java.util.List;

import static util.ApiResponse.success;

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
     *
     * @return*/
    @PostMapping
    public ApiResponse<Boolean> makeOrder(@RequestBody OrderRequestDto orderRequestDto) {
        orderService.readyToOrder(orderRequestDto);
        return success(true);
    }

    @GetMapping("/{id}")
    public ApiResponse<Boolean> cancelOrder(@PathVariable Long id) {
        orderService.cancel(id);
        return success(true);
    }

    @GetMapping("/list")
    public ApiResponse<List<OrderResponseDto>> listOrder() {
        Long userId = securityService.findUserIdbyToken();
        List<OrderResponseDto> orderList = orderService.getOrderList(userId);
        return success(orderList);
    }
}
