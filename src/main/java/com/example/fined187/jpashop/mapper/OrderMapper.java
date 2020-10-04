package com.example.fined187.jpashop.mapper;

import com.example.fined187.jpashop.domain.dto.OrderDto;
import com.example.fined187.jpashop.domain.dto.OrderItemDto;
import com.example.fined187.jpashop.domain.entity.Order;
import com.example.fined187.jpashop.domain.entity.OrderItem;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class OrderMapper implements EntityMapper<Order, OrderDto> {
    @Override
    public Order toEntity(OrderDto dto) {

        return null;
    }

    @Override
    public OrderDto toDto(Order entity) {
        List<OrderItem> orderItems = entity.getOrderItems();
        List<OrderItemDto> orderItemDtos = new ArrayList<>();

        for(OrderItem orderItem : orderItems) {
            OrderItemDto orderItemDto = OrderItemDto
                    .builder()
                    .itemId(orderItem.getId())
                    .count(orderItem.getOrderItemCount())
                    .price(orderItem.getOrderItemPrice())
                    .build();

            orderItemDtos.add(orderItemDto);
        }

        OrderDto orderDto = OrderDto.builder()
                .memberId(entity.getId())
                .city(entity.getMember().getAddress().getCity())
                .street(entity.getMember().getAddress().getStreet())
                .zipcode(entity.getMember().getAddress().getZipcode())
                .build();
        return null;
    }
}
