package com.shop.online.shopingMall.service;

import com.shop.online.shopingMall.domain.Order;
import com.shop.online.shopingMall.domain.OrderItem;
import com.shop.online.shopingMall.domain.Product;
import com.shop.online.shopingMall.domain.User;
import com.shop.online.shopingMall.dto.OrderItemDto;
import com.shop.online.shopingMall.dto.OrderRequestDto;
import com.shop.online.shopingMall.exception.NotFoundBillingInfoException;
import com.shop.online.shopingMall.exception.NotFoundUserException;
import com.shop.online.shopingMall.exception.ProductNotFoundException;
import com.shop.online.shopingMall.repository.BillingInfoRepository;
import com.shop.online.shopingMall.repository.OrderRepository;
import com.shop.online.shopingMall.repository.ProductRepository;
import com.shop.online.shopingMall.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    public void createOrder(OrderRequestDto orderRequestDto) {
        User user = userRepository.findById((long) orderRequestDto.getUserId()).orElseThrow(NotFoundUserException::new);
        Product product = productRepository.findById((long) orderRequestDto.getProductId()).orElseThrow(ProductNotFoundException::new);

        List<OrderItem> orderItems = OrderItemDto.toEntity(orderRequestDto.getItemList());

        Order order = Order.createOrder(user, product, orderItems);
        orderRepository.save(order);
    }

}
