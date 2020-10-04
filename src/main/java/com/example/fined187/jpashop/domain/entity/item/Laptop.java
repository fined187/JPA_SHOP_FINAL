package com.example.fined187.jpashop.domain.entity.item;

import com.example.fined187.jpashop.domain.dto.ItemDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("laptop")
@NoArgsConstructor
@Getter @Setter
public class Laptop extends Item{
    private String code;

    @Builder
    public Laptop(String itemName, int itemPrice, int itemCount, String code) {
        super(itemName, itemPrice, itemCount);
        this.code = code;

    }

    @Override
    public void changeInfo(ItemDto itemDto) {
        super.changeInfo(itemDto);
        this.code = itemDto.getCode();
    }
}
