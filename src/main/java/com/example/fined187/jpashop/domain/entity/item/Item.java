package com.example.jpashop.domain.entity.item;

import com.example.fined187.jpashop.domain.dto.ItemDTO;
import com.example.fined187.jpashop.exception.NotEnoughException;
import com.example.jpashop.domain.entity.BaseDataEntity;
import com.example.jpashop.domain.entity.OrderItem;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "items")
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn
@Data
public abstract class Item extends BaseDataEntity {
    @Id @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    @OneToMany(mappedBy = "item")
    List<OrderItem> orderItems = new ArrayList<>();

    private String itemName;
    private int itemPrice;
    private int itemCount;

    public Item(String itemName, int itemCount, int itemPrice) {
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.itemCount = itemCount;
    }

    //  Domain Logic
//  재고 감소.
    public void removeStock(int orderItemCount) {
        int restStock = this.itemCount - orderItemCount;            //  나머지 재고 = 현재 itemCount - 주문 itemCount

//      Todo API 협의 후 예외처리 보완.
        if(restStock < 0) {
            throw new NotEnoughException("재고가 부족합니다. 현재 재고량 : " +
                    getItemCount());
        }
    }

    //  재고 증가.
    public void addStock(int count) {
        this.itemCount += count;
    }

    public ItemDTO toDTO() {
        ItemDTO itemDTO = new ItemDTO();
        return itemDTO;
    }

    public void changeInfo(ItemDTO itemDTO) {

        this.itemName = itemDTO.getItemName();
        this.itemCount = itemDTO.getItemCount();
        this.itemPrice = itemDTO.getItemPrice();
    }



}
