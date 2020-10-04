package com.example.fined187.jpashop.service;

import com.example.fined187.jpashop.domain.dto.OrderDto;
import com.example.fined187.jpashop.domain.dto.OrderItemDto;
import com.example.fined187.jpashop.domain.entity.*;
import com.example.fined187.jpashop.domain.entity.item.Item;
import com.example.fined187.jpashop.domain.enums.DeliveryStatus;
import com.example.fined187.jpashop.exception.NotFoundException;
import com.example.fined187.jpashop.mapper.OrderMapper;
import com.example.fined187.jpashop.repository.ItemRepository;
import com.example.fined187.jpashop.repository.MemberRepository;
import com.example.fined187.jpashop.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderService {
    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;
    private final OrderMapper orderMapper;

    public OrderDto getOrder(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Not Found id: " + id));

        return orderMapper.toDto(order);
    }

    public List<OrderDto> getOrders() {
        List<Order> orders = Optional.of(orderRepository.findAll())
                .orElse(Collections.emptyList());

        return orders.stream()
                .map(orderMapper::toDto)
                .collect(Collectors.toList());
    }

    //  핵심 로직
    @Transactional
    public Long order(OrderDto orderDto) {
        Member member = memberRepository.findById(orderDto.getMemberId())
                .orElseThrow(() -> new NotFoundException("Not Found id: " + orderDto.getMemberId()));

//     배송 정보의 address 객체 생성.
        Address address = Address.builder()
                .city(orderDto.getCity())
                .street(orderDto.getStreet())
                .zipcode(orderDto.getZipcode())
                .build();

//     배송 정보 객체 생성.
        Delivery delivery = Delivery.builder()
                .deliveryStatus(DeliveryStatus.DELIVERYING)
                .address(address)
                .build();

        List<OrderItemDto> orderItemDtoList = orderDto.getOrderItemDTOList();

        List<OrderItem> orderItems = new ArrayList<>();

        for (OrderItemDto orderItemDto : orderItemDtoList) {

//           OrderItemDto의 정보를 이용해서 Item Entity의 정보를 조회.
            Item item = itemRepository.findById(orderItemDto.getItemId())
                    .orElseThrow(() -> new NotFoundException("Not Found id: " + orderItemDto.getItemId()));

//          OrderItemDto, Item Entity의 정보를 이용해서 OrderItem 객체 생성.
            OrderItem orderItem = OrderItem.builder()
                    .orderItemPrice(orderItemDto.getPrice())
                    .orderItemCount(orderItemDto.getCount())
                    .build();

            orderItem.setItem(item);
            orderItems.add(orderItem);
        }
        Order order = Order.createOrder(member, delivery, orderItems);

        return orderRepository.save(order).getId();
    }

    @Transactional
    public void cancel(Long orderId) {
//     주문 Entity 조회.
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new NotFoundException("Not Found id: " + orderId));
//      주문 취소
        order.orderCancel();
    }

}
