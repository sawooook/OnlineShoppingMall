package com.shop.online.shopingMall.controller;

import com.shop.online.shopingMall.dto.order.OrderRequestDto;
import com.shop.online.shopingMall.dto.order.OrderResponseDto;
import com.shop.online.shopingMall.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import com.shop.online.shopingMall.util.ApiResponse;

import java.util.List;

import static com.shop.online.shopingMall.util.ApiResponse.success;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;



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
    public ApiResponse<List<OrderResponseDto>> listOrder(@RequestParam Long userId) {
        List<OrderResponseDto> orderList = orderService.getOrderList(userId);
        return success(orderList);
    }
}
