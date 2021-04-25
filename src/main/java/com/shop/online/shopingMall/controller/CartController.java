package com.shop.online.shopingMall.controller;

import com.shop.online.shopingMall.domain.Cart;
import com.shop.online.shopingMall.dto.cart.CartRequestDto;
import com.shop.online.shopingMall.dto.cart.CartResponseDto;
import com.shop.online.shopingMall.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import util.ApiResponse;

import static util.ApiResponse.*;

@RestController
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @PostMapping
    public ApiResponse<String> addCart(@RequestBody CartRequestDto cartRequestDto) {
        Cart cart = CartRequestDto.toEntity(cartRequestDto);
        cartService.save(cart);
        return success("장바구니 등록에 성공하셨습니다.");
    }

    /**
     * 장바구니 RESET Controller
     *
     * @return*/
    @GetMapping("/list/{id}")
    public ApiResponse<CartResponseDto> showCartList(@PathVariable String id) {
        Cart cart = cartService.findById(id);
        CartResponseDto responseDto = CartResponseDto.toDto(cart);
        return success(responseDto);
    }

    /**
    * 장바구니 RESET Controller
    *
     * @return*/
    @DeleteMapping("/{id}")
    public ApiResponse<String> resetCart(@PathVariable String id) {
        cartService.resetCart(id);
        return success("장바구니를 모두 비웠습니다.");
    }
}