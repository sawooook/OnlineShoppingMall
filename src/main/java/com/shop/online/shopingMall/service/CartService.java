package com.shop.online.shopingMall.service;

import com.shop.online.shopingMall.domain.Cart;
import com.shop.online.shopingMall.domain.CartItem;
import com.shop.online.shopingMall.dto.cart.CartRequestDto;
import com.shop.online.shopingMall.repository.CartRepository;
import com.shop.online.shopingMall.exception.NotFoundCartException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class CartService {

    private final CartRepository cartRepository;

    public Cart save(Cart cart) {
        return cartRepository.save(cart);
    }

    public Cart findById(String id) {
        return cartRepository.findById(id).orElseThrow(NotFoundCartException::new);
    }

    /**
    * 카드를 비우는 메소드
    *
     * @return*/
    public boolean deleteCart(String id) {
        Cart cart = cartRepository.findById(id)
                .orElseThrow(() -> new NotFoundCartException("장바구니에 제품이 등록 되어있지 않습니다."));
        cartRepository.delete(cart);
        return true;
    }

    /**
    * 카드에 저장하는 메소드
    *
     * @return*/
    public boolean save(CartRequestDto cartRequestDto) {
        List<CartItem> itemList = cartRequestDto.getCartItems()
                .stream().map(cartItemDto -> new CartItem(cartItemDto.getColor(), cartItemDto.getSize(), cartItemDto.getPrice())).collect(Collectors.toList());
        cartRepository.save(new Cart(cartRequestDto.getId(), cartRequestDto.getName(), itemList));
        return true;
    }
}
