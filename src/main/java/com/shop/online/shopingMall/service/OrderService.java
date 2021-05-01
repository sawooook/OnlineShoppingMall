package com.shop.online.shopingMall.service;

import com.shop.online.shopingMall.domain.*;
import com.shop.online.shopingMall.domain.enumType.DeliveryStatus;
import com.shop.online.shopingMall.domain.enumType.OrderStatus;
import com.shop.online.shopingMall.dto.order.*;
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

    /**
    *  주문 준비를 위한 메소드
     *  1) 활성화된 카드가 있는지 확인한다
     *  2) 활성화된 카드가 있으면 주문을 진행한다.
    * */

    @Transactional(rollbackFor = Exception.class)
    public void readyToOrder(OrderRequestDto orderRequestDto) {

        // 활성화된 카드가 있는지 확인하고 없을 경우 등록된 카드가 없다고 return
        User user = userRepository.findUserAndActiveBillingInfo(orderRequestDto.getUserId())
                .orElseThrow(() -> new NotActiveBillingInfoExcption("등록된 카드가 없습니다."));

        Product product = productRepository.findById((long) orderRequestDto.getProductId())
                .orElseThrow(ProductNotFoundException::new);

        List<OrderItem> items = orderRequestDto.getItemList().stream().map(
                orderItemDto -> new OrderItem(orderItemDto.getSize(), orderItemDto.getColor()))
                .collect(Collectors.toList());

        Order order = new Order(user, user.activeBillingInfo().get() ,product, items, new Address(orderRequestDto.getAddressCode(), orderRequestDto.getAddressDetail()));
        orderRepository.save(order);

        OrderResultResponseDto result = readyToPay(order);
        Payment payment = makePayBilling(result);
        readToDelivery(payment);
    }

    /**
    * 아이디를 통해서 해당 주문을 찾는다.
    * */
    public Order findOrder(Long id) {
        return orderRepository.findById(id).orElseThrow(NotFoundOrderExcption::new);
    }

    /**
    * 활성화된 정기결제 카드로 결제를 시도한다.
     * @param order
     * @return*/
    private OrderResultResponseDto readyToPay(Order order) {
        return billingInfoService.charge(order);
    }

    /**
    * 배달 상태를 ready로 변경을 완료 할 시
    * 주문의 상태도 ready로 변경한다.
    * */
    private void readToDelivery(Payment payment) {
        Order order = payment.getOrder();
        Address address = order.getUser().getAddress();

        Delivery delivery = new Delivery(new Address(address.getAddressCode(), address.getAddressDetail()), order);
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

    /**
    * 취소 요청 받은 주문을 찾은 후 주문의 상태를 조회한다.
    * */
    @Transactional
    public void cancel(Long id) {
        Order order = orderRepository.findById(id).orElseThrow(ProductNotFoundException::new);
        if (order.getDelivery().isDeliveryGoing()) {
            throw new OrderCancelFail("현재 배송중이라 주문을 취소 할수 없습니다");
        } else {
            if (order.isSuccess()) {
                throw new OrderCancelFail("배송이 이미 완료된 제품입니다.");
            } else if (order.isCancel()) {
                throw new OrderCancelFail("배송이 취소된 제품입니다.");
            } else if (order.isReady()){
                order.cancel();
            }
        }
    }

    public List<OrderResponseDto> getOrderList(Long userId) {
        List<Order> orderList = orderRepository.findOrderList(userId);
        return orderList.stream().map(order -> new OrderResponseDto(order)).collect(Collectors.toList());
    }
}
