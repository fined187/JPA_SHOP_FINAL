package com.example.jpashop.domain.entity;

import com.example.fined187.jpashop.domain.enums.DeliveryStatus;
import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Delivery {

    @Id @GeneratedValue
    @Column(name = "delivery_id")
    private Long id;

    @OneToOne(mappedBy = "delivery")
    @Setter
    private com.example.jpashop.domain.entity.Order order;

    @Embedded
    private com.example.jpashop.domain.entity.Address address;

    @Enumerated(EnumType.STRING)
    private DeliveryStatus deliveryStatus;

    @Builder
    public Delivery(com.example.jpashop.domain.entity.Address address, DeliveryStatus deliveryStatus) {
        this.address = address;
        this.deliveryStatus = deliveryStatus;
    }
}
