package com.example.fined187.jpashop.domain.dto;

import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
public class OrderDto {

    private Long memberId;
    private String street;
    private String city;
    private String zipcode;

    private List<OrderItemDto> orderItemDTOList = new ArrayList<>();
}
