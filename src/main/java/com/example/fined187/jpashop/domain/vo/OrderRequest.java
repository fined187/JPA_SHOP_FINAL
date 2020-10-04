package com.example.fined187.jpashop.domain.vo;

import com.example.fined187.jpashop.domain.entity.OrderItem;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class OrderRequest {
    private Long memberId;
    private String street;
    private String zipcode;
    private String city;

    private final List<OrderItem> orderItems = new ArrayList<>();

    @Getter
    public static class OrderItemVO {
        private Long itemId;
        private int price;
        private int count;
    }
}
