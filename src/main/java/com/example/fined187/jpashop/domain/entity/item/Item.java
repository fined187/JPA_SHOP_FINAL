package com.example.fined187.jpashop.domain.entity.item;

import com.example.fined187.jpashop.domain.dto.ItemDto;
import com.example.fined187.jpashop.domain.entity.BaseEntity;
import com.example.fined187.jpashop.domain.entity.OrderItem;
import com.example.fined187.jpashop.exception.NotEnoughException;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

//@Entity
@Table(name = "items")
@Inheritance(strategy = InheritanceType.JOINED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public abstract class Item extends BaseEntity {
    @Id @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    @OneToMany(mappedBy = "item")
    List<OrderItem> orderItems = new ArrayList<>();

    private String itemName;
    private int itemPrice;
    private int itemCount;

    public Item(String itemName, int itemPrice, int itemCount) {
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.itemCount = itemCount;
    }

//  Domain Logic
//  재고 감소.
    public void removeStock(int orderItemCount) {
        int restStock = this.itemCount - orderItemCount;

        if(restStock < 0) {
            throw new NotEnoughException("재고가 부족합니다. 현재 재고량 : " + getItemCount());
        }
    }

//  재고 증가.
    public void addStock(int itemCount) {
        this.itemCount += itemCount;
    }

    public ItemDto toDto() {
        ItemDto itemDto = new ItemDto();

        return itemDto;
    }

    public void changeInfo(ItemDto itemDto) {
        this.itemCount = itemDto.getItemCount();
        this.itemName = itemDto.getItemName();
        this.itemPrice = itemDto.getItemPrice();
    }
}
