package com.shop.online.shopingMall.service;

import com.shop.online.shopingMall.domain.*;
import com.shop.online.shopingMall.dto.OrderItemDto;
import com.shop.online.shopingMall.dto.OrderRequestDto;
import com.shop.online.shopingMall.dto.order.OrderResponseDto;
import com.shop.online.shopingMall.dto.user.AddressDto;
import com.shop.online.shopingMall.exception.*;
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

    /**
    *  주문 준비를 위한 메소드
     *  1) 활성화된 카드가 있는지 확인한다
     *  2) 활성화된 카드가 있으면 주문을 진행한다.
    * */

    public void readyToOrder(OrderRequestDto orderRequestDto) {
        User user = userRepository.findById((long) orderRequestDto.getUserId()).orElseThrow(NotFoundUserException::new);

        // 해당 유저에게 활성화된 카드가 있는지 확인한다.
        // 없을 경우 에러를 리턴 한다.
        if (user.activeBillingInfo().isEmpty()) {
            throw  new NotFoundBillingInfoException("등록된 카드가 없습니다. 카드를 등록해주세요");
        }

        Product product = productRepository.findById((long) orderRequestDto.getProductId()).orElseThrow(ProductNotFoundException::new);
        List<OrderItem> orderItems = OrderItemDto.toEntity(orderRequestDto.getItemList());

        Address address = AddressDto.toEntity(new Address(orderRequestDto.getAddressCode(), orderRequestDto.getAddressDetail()));

        Order order = makeOrder(user, product, orderItems, address);

        orderRepository.save(order);
        readyToPay(order);
    }

    public Order findOrder(Long id) {
        return orderRepository.findById(id).orElseThrow(NotFoundOrderExcption::new);
    }

    /**
    * 활성화된 카드로 정기결제를 시도한다.
    *
     * @param order*/
    private void readyToPay(Order order) {
        billingInfoService.charge(order);
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
    private Order makeOrder(User user, Product product, List<OrderItem> orderItems, Address address) {
        Order order = Order.createOrder(user, product, orderItems, address);
        return order;
    }

}
