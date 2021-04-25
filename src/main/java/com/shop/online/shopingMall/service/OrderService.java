package com.shop.online.shopingMall.service;

import com.shop.online.shopingMall.domain.*;
import com.shop.online.shopingMall.domain.enumType.DeliveryStatus;
import com.shop.online.shopingMall.dto.order.OrderItemDto;
import com.shop.online.shopingMall.dto.order.OrderRequestDto;
import com.shop.online.shopingMall.dto.order.OrderResponseDto;
import com.shop.online.shopingMall.dto.order.OrderResultResponseDto;
import com.shop.online.shopingMall.dto.user.AddressDto;
import com.shop.online.shopingMall.exception.*;
import com.shop.online.shopingMall.repository.OrderRepository;
import com.shop.online.shopingMall.repository.ProductRepository;
import com.shop.online.shopingMall.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final BillingInfoService billingInfoService;
    private final PaymentService paymentService;
    private final DeliveryService deliveryService;

    /**
    *  주문 준비를 위한 메소드
     *  1) 활성화된 카드가 있는지 확인한다
     *  2) 활성화된 카드가 있으면 주문을 진행한다.
    * */

    @Transactional(rollbackFor = Exception.class)
    public void readyToOrder(OrderRequestDto orderRequestDto) {
        User user = userRepository.findUserAndActiveBillingInfo(orderRequestDto.getUserId()).orElseThrow(NotOrderException::new);

        Product product = productRepository.findById((long) orderRequestDto.getProductId()).orElseThrow(ProductNotFoundException::new);
        List<OrderItem> items = orderRequestDto.getItemList().stream().map(orderItemDto -> new OrderItem(orderItemDto.getSize(), orderItemDto.getColor())).collect(Collectors.toList());
        Order order = new Order(user, product, items, new Address(orderRequestDto.getAddressCode(), orderRequestDto.getAddressDetail()));
        orderRepository.save(order);

        OrderResultResponseDto result = readyToPay(order);
        Payment payment = makePayBilling(result);
        readToDelivery(payment);
    }

    public Order findOrder(Long id) {
        return orderRepository.findById(id).orElseThrow(NotFoundOrderExcption::new);
    }

    /**
    * 활성화된 카드로 정기결제를 시도한다.
    *
     * @param order
     * @return*/
    private OrderResultResponseDto readyToPay(Order order) {
        return billingInfoService.charge(order);
    }

    private void readToDelivery(Payment payment) {
        Order order = payment.getOrder();
        Address address = order.getUser().getAddress();


        Delivery delivery = Delivery.builder().deliveryStatus(DeliveryStatus.ready)
                .address(new Address(address.getAddressCode(), address.getAddressDetail())).build();


        deliveryService.save(delivery);
        order.setDelivery(delivery);
        order.updateOrderStatus();
    }

    private Payment makePayBilling(OrderResultResponseDto result) {
        Order order = findOrder(Long.valueOf(result.getPartnerOrderId()));
        result.setOrder(order);
        Payment payment = OrderResultResponseDto.toEntity(result);
        paymentService.save(payment);
        return payment;
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
        BillingInfo billingInfo = billingInfoService.isActiveBillingInfo(user);
        Order order = Order.createOrder(user, product, orderItems, address, billingInfo);
        return order;
    }
}
