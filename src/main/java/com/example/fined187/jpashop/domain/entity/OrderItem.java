package com.example.jpashop.domain.entity;

import com.example.jpashop.domain.entity.item.Item;
import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class OrderItem {

    @Id @GeneratedValue
    @Column(name = "order_item_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    @Setter
    private com.example.jpashop.domain.entity.Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    @Setter
    private Item item;

    private int orderItemPrice;
    private int orderItemCount;

    @Builder
    public OrderItem(int orderItemPrice, int orderItemCount) {
        this.orderItemPrice = orderItemPrice;
        this.orderItemCount = orderItemCount;
    }

    //  연관 관계 편의 메서드
    public void setItem(Item item) {
        this.item = item;
        item.removeStock(getOrderItemCount());
    }

    public void cancel() {
        getItem().addStock(getOrderItemCount());
    }

}
