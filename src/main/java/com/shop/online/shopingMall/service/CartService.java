package com.shop.online.shopingMall.service;

import com.shop.online.shopingMall.domain.Cart;
import com.shop.online.shopingMall.domain.CartItem;
import com.shop.online.shopingMall.domain.CartRepository;
import com.shop.online.shopingMall.exception.NotFoundCartException;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.jdo.annotations.Cacheable;
import javax.persistence.EntityNotFoundException;

@Service
@RequiredArgsConstructor
@Transactional
public class CartService {

    private final CartRepository cartRepository;
    private final RedisTemplate redisTemplate;

    /*
    * @cachable -> 캐시할 수 있는 메서드를 정의하는데 사용
    * 해당 어노테이션이 적힌 메서드는 redis에 먼저 가서 값을 찾고 없으면 DB에가서 값을 가져옴
    * */
//    @Transactional(readOnly = true)
//    @Cacheable("cart") // id 값이 cart로 들어감
//    public Cart getCartList(String id) {
//        return cartRepository.findById(id).orElseThrow(EntityNotFoundException::new);
//    }

    public Cart save(Cart cart) {
        return cartRepository.save(cart);
    }

    public Cart findById(String id) {
        return cartRepository.findById(id).orElseThrow(NotFoundCartException::new);
    }

    /**
    * 카드를 비우는 메소드
    * */
    public void resetCart(String id) {
        Cart cart = cartRepository.findById(id).orElseThrow(() -> new NotFoundCartException("장바구니가 비워져있습니다."));
        cartRepository.delete(cart);
    }
}
