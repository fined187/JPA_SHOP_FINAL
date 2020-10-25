package com.example.fined187.jpashop.domain.entity;

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
    private Order order;

    @Embedded
    private Address address;

    @Enumerated(EnumType.STRING)
    private DeliveryStatus deliveryStatus;

    @Builder
    public Delivery(Address address, DeliveryStatus deliveryStatus) {
        this.address = address;
        this.deliveryStatus = deliveryStatus;
    }
}
