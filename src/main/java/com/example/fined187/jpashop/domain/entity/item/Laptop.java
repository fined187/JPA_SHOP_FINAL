package com.example.jpashop.domain.entity.item;

import com.example.fined187.jpashop.domain.dto.ItemDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@DiscriminatorValue("laptop")
@NoArgsConstructor
@Getter @Setter
public class Laptop extends com.example.jpashop.domain.entity.item.Item {
    private String code;

    @Builder
    public Laptop(String itemName, int itemCount, int itemPrice, String name, String code) {
        super(itemName, itemCount, itemPrice);
        this.code = code;
    }


    @Override
    public void changeInfo(ItemDTO itemDTO) {
        super.changeInfo(itemDTO);
        this.code = itemDTO.getCode();
    }
}
