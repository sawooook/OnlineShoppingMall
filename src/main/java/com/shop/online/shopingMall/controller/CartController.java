package com.shop.online.shopingMall.controller;

import com.shop.online.shopingMall.domain.Board;
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

    /**
    * 장바구니에 추가하는 API
    * */
    @PostMapping
    public ApiResponse<Boolean> addCart(@RequestBody CartRequestDto cartRequestDto) {
        boolean status = cartService.save(cartRequestDto);
        return success(status);
    }

    /**
     * 장바구니에 담긴 데이터들을 보여주는 API
     *
     * @return*/
    @GetMapping("/list/{id}")
    public ApiResponse<CartResponseDto> showCartList(@PathVariable String id) {
        Cart cart = cartService.findById(id);
        return success(new CartResponseDto(cart));
    }

    /**
    * 장바구니에서 등록한 아이템을 지우는 API
    *
     * @return*/
    @DeleteMapping("/{id}")
    public ApiResponse<Boolean> deleteCart(@PathVariable String id) {
        cartService.deleteCart(id);
        return success(true);
    }
}