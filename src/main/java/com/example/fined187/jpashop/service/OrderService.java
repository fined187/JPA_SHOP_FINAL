package com.example.jpashop.service;

import com.example.jpashop.domain.dto.OrderDTO;
import com.example.jpashop.domain.dto.OrderItemDTO;
import com.example.jpashop.domain.entity.*;
import com.example.jpashop.domain.entity.item.Item;
import com.example.jpashop.domain.enums.DeliveryStatus;
import com.example.jpashop.exception.NotFoundException;
import com.example.jpashop.mapper.OrderMapper;
import com.example.jpashop.repository.ItemRepository;
import com.example.jpashop.repository.MemberRepository;
import com.example.jpashop.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor            //  final이 붙은 애들만.
@Transactional(readOnly = true)
public class OrderService {

    //   1개의 Service에는 1개의 Repository
//    private final ItemRepository itemRepository;
    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;
    private final OrderMapper orderMapper;

    public OrderDTO getOrder(Long id){
        com.example.jpashop.domain.entity.Order order = orderRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(
                        "not found order. id: " + id));

        return orderMapper.toDto(order);
    }

    public List<OrderDTO> getOrders() {
        List<com.example.jpashop.domain.entity.Order> orders = Optional.of(orderRepository.findAll())
                .orElse(Collections.emptyList());

        return orders.stream()
                .map(orderMapper::toDto)
                .collect(Collectors.toList());
    }

    //  핵심 로직.
    @Transactional
    public Long order(OrderDTO orderDTO){

//      주문한 회원정보 조회.
        com.example.jpashop.domain.entity.Member member = memberRepository.findById(orderDTO.getMemberId())
                .orElseThrow(() -> new NotFoundException(
                        "Not found member. id: " + orderDTO.getMemberId()));

//      배송정보의 address 객체 생성
        com.example.jpashop.domain.entity.Address address = Address.builder()
                .city(orderDTO.getCity())
                .street(orderDTO.getStreet())
                .zipcode(orderDTO.getZipcode())
                .build();

//       배송정보 객체 생성
        Delivery delivery = Delivery
                .builder()
                .deliveryStatus(DeliveryStatus.DELIVERTING)
                .address(address)
                .build();

        List<OrderItemDTO> orderItemDTOList = orderDTO.getOrderItemDTOList();

        List<OrderItem> orderItems = new ArrayList<>();
        for (OrderItemDTO orderItemDTO : orderItemDTOList) {

//          OrderItemDto의 정보를 이용해서
//          Item Entity 정보 조회
            Item item = itemRepository.findById(orderItemDTO.getItemId())
                    .orElseThrow(() -> new NotFoundException(
                            "not found item. id: " + orderItemDTO.getItemId()));

//          OrderItemDto, Item Entity 정보를 활용하여
//          OrderItem 객체 생성
            OrderItem orderItem = OrderItem
                    .builder()
                    .orderItemPrice(orderItemDTO.getPrice())
                    .orderItemCount(orderItemDTO.getCount())
                    .build();
            orderItem.setItem(item);
//          저장
            orderItems.add(orderItem);
        }

        com.example.jpashop.domain.entity.Order order = Order.createOrder(member, delivery, orderItems);
        return orderRepository.save(order).getId();
    }

    @Transactional
    public void cancel(Long orderId) {
        //주문 엔티티 조회
        com.example.jpashop.domain.entity.Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new NotFoundException(
                        "not found order. id: " + orderId));

        //주문 취소
        order.orderCancel();
    }
}
