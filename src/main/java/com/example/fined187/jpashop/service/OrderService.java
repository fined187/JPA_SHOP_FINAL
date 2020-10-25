package com.example.fined187.jpashop.service;

import com.example.fined187.jpashop.domain.dto.OrderDTO;
import com.example.fined187.jpashop.domain.dto.OrderItemDTO;
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
@RequiredArgsConstructor            //  final이 붙은 애들만.
@Transactional(readOnly = true)
public class OrderService {

    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;
    private final OrderMapper orderMapper;

    public OrderDTO getOrder(Long id){
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(
                        "not found order. id: " + id));

        return orderMapper.toDto(order);
    }

    public List<OrderDTO> getOrders() {
        List<Order> orders = Optional.of(orderRepository.findAll())
                .orElse(Collections.emptyList());

        return orders.stream()
                .map(orderMapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public Long order(OrderDTO orderDTO){

        Member member = memberRepository.findById(orderDTO.getMemberId())
                .orElseThrow(() -> new NotFoundException(
                        "Not found member. id: " + orderDTO.getMemberId()));

        Address address = Address.builder()
                .city(orderDTO.getCity())
                .street(orderDTO.getStreet())
                .zipcode(orderDTO.getZipcode())
                .build();

        Delivery delivery = Delivery
                .builder()
                .deliveryStatus(DeliveryStatus.DELIVERYING)
                .address(address)
                .build();

        List<OrderItemDTO> orderItemDTOList = orderDTO.getOrderItemDTOList();

        List<OrderItem> orderItems = new ArrayList<>();
        for (OrderItemDTO orderItemDTO : orderItemDTOList) {

            Item item = itemRepository.findById(orderItemDTO.getItemId())
                    .orElseThrow(() -> new NotFoundException(
                            "not found item. id: " + orderItemDTO.getItemId()));

            OrderItem orderItem = OrderItem
                    .builder()
                    .orderItemPrice(orderItemDTO.getPrice())
                    .orderItemCount(orderItemDTO.getCount())
                    .build();
            orderItem.setItem(item);
            orderItems.add(orderItem);
        }

        Order order = Order.createOrder(member, delivery, orderItems);
        return orderRepository.save(order).getId();
    }

    @Transactional
    public void cancel(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new NotFoundException(
                        "not found order. id: " + orderId));

        order.orderCancel();
    }
}
