package com.shop.online.shopingMall.service;

import com.shop.online.shopingMall.domain.Order;
import com.shop.online.shopingMall.domain.OrderItem;
import com.shop.online.shopingMall.domain.Product;
import com.shop.online.shopingMall.domain.User;
import com.shop.online.shopingMall.dto.OrderItemDto;
import com.shop.online.shopingMall.dto.OrderRequestDto;
import com.shop.online.shopingMall.dto.order.OrderResponseDto;
import com.shop.online.shopingMall.exception.NotFoundUserException;
import com.shop.online.shopingMall.exception.OrderCancelFail;
import com.shop.online.shopingMall.exception.ProductNotFoundException;
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
    private final BillingInfoService billingInfoService;


    public void readyToOrder(OrderRequestDto orderRequestDto) {
        User user = userRepository.findById((long) orderRequestDto.getUserId()).orElseThrow(NotFoundUserException::new);
        Product product = productRepository.findById((long) orderRequestDto.getProductId()).orElseThrow(ProductNotFoundException::new);
        List<OrderItem> orderItems = OrderItemDto.toEntity(orderRequestDto.getItemList());
        Order order = makeOrder(user, product, orderItems);
        orderRepository.save(order);

        readytoPay();
    }

    private void readytoPay() {
        billingInfoService.ready();
    }

    public void cancel(Long id) {
        Order order = orderRepository.findById(id).orElseThrow(ProductNotFoundException::new);
        boolean cancel = order.cancel();
        if (!cancel) {
            throw new OrderCancelFail("주문을 취소 할수 없습니다");
        }
    }

    public List<OrderResponseDto> getOrderList(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(NotFoundUserException::new);
        List<Order> orderList = user.getOrderList();
        List<OrderResponseDto> responseDtoList = OrderResponseDto.toDto(orderList);
        return responseDtoList;
    }




    /**
    *  주문을 생성하고, 생성이 완료될 시 주문의 상태를 Ready로 변경한다
    * */
    private Order makeOrder(User user, Product product, List<OrderItem> orderItems) {
        Order order = Order.createOrder(user, product, orderItems);
        return order;
    }

}
