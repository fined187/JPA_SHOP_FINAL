package com.example.fined187.jpashop.domain.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderItemDto {
    private Long itemId;
    private int count;
    private int price;
}
