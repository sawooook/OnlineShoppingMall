package com.shop.online.shopingMall.controller;

import com.shop.online.shopingMall.concern.ResponseMessage;
import com.shop.online.shopingMall.concern.ResponseStatus;
import com.shop.online.shopingMall.domain.Cart;
import com.shop.online.shopingMall.dto.cart.CartRequestDto;
import com.shop.online.shopingMall.dto.cart.CartResponseDto;
import com.shop.online.shopingMall.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @PostMapping
    public ResponseEntity addCart(@RequestBody CartRequestDto cartRequestDto) {
        Cart cart = CartRequestDto.toEntity(cartRequestDto);
        cartService.save(cart);
        return ResponseEntity.ok().body(new ResponseMessage(ResponseStatus.OK,"장바구니 등록에 성공하였습니다.",null));
    }

    @GetMapping("/list/{id}")
    public ResponseEntity showCartList(@PathVariable String id) {
        System.out.println(cartService.findById(id));
        Cart cart = cartService.findById(id);

        System.out.println("=============================================");
        System.out.println(cart.getName());
        System.out.println(cart.getId());
        System.out.println(cart.getCartItems().get(0).getColor());

        CartResponseDto responseDto = CartResponseDto.toDto(cart);
        return ResponseEntity.ok().body(responseDto);
    }
}