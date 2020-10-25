package com.example.fined187.jpashop.mapper;

import com.example.fined187.jpashop.domain.dto.OrderDTO;
import com.example.fined187.jpashop.domain.dto.OrderItemDTO;
import com.example.fined187.jpashop.domain.entity.Order;
import com.example.fined187.jpashop.domain.entity.OrderItem;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class OrderMapper implements EntityMapper<Order, OrderDTO> {
    @Override
    public Order toEntity(OrderDTO dto) {
        return null;
    }

    @Override
    public OrderDTO toDto(Order entity) {

        List<OrderItem> orderItems = entity.getOrderItems();
        List<OrderItemDTO> orderItemDTOS = new ArrayList<>();

        for (OrderItem orderItem : orderItems) {
            OrderItemDTO orderItemDTO = OrderItemDTO
                    .builder()
                    .itemId(orderItem.getId())
                    .count(orderItem.getOrderItemCount())
                    .price(orderItem.getOrderItemPrice())
                    .build();

            orderItemDTOS.add(orderItemDTO);
        }

        OrderDTO orderDTO = OrderDTO.builder()
                .memberId(entity.getMember().getId())
                .city(entity.getMember().getAddress().getCity())
                .street(entity.getMember().getAddress().getStreet())
                .zipcode(entity.getMember().getAddress().getZipcode())
                .orderItemDTOList(orderItemDTOS)
                .build();
        return null;
    }
}
