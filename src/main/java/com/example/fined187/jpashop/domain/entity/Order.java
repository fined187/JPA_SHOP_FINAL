package com.example.fined187.jpashop.domain.entity;

import com.example.fined187.jpashop.domain.enums.DeliveryStatus;
import com.example.fined187.jpashop.domain.enums.OrderStatus;
import lombok.*;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Order {

    @Id @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    @OneToMany(mappedBy = "order")
    List<OrderItem> orderItems = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "delivet_id")
    private Delivery delivery;

    private LocalDateTime orderTime;
    private int orderPrice;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    public void setMember(Member member) {
        this.member = member;
        member.getOrders().add(this);
    }

    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
        delivery.setOrder(this);
    }

    public void addOrderItem(OrderItem orderItem) {
        orderItem.setOrder(this);
        this.orderItems.add(orderItem);
    }

    @Builder
    public Order(LocalDateTime orderTime, OrderStatus orderStatus) {
        this.orderTime = orderTime;
        this.orderStatus = orderStatus;
    }

    public static Order createOrder(Member member, Delivery delivery, List<OrderItem> orderItems) {
        Order order = Order
                .builder()
                .orderTime(LocalDateTime.now())
                .orderStatus(OrderStatus.ORDERED)
                .build();

        order.setMember(member);
        order.setDelivery(delivery);

        for(OrderItem orderItem : orderItems) {
            order.addOrderItem(orderItem);
        }
        return order;
    }

    public int totalPrice() {
        return this.getOrderItems().stream()
                .mapToInt(OrderItem::getOrderItemPrice)
                .sum();
    }

    public void orderCancel() {
        if(this.delivery.getDeliveryStatus() == DeliveryStatus.DELIVERED ||
                this.delivery.getDeliveryStatus() == DeliveryStatus.DELIVERYING) {
            throw new IllegalStateException("배송중이거나 배송 완료된 상품은 주문 취소 할 수 없습니다.");
        }
        getOrderItems().forEach(OrderItem::cancel);
    }
}
