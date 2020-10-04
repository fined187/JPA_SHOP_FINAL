package com.example.fined187.jpashop.domain.entity.item;

import com.example.fined187.jpashop.domain.dto.ItemDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("album")
@NoArgsConstructor
@Getter @Setter
public class Album extends Item{
    private String artist;

    @Builder
    public Album(String itemName, int itemPrice, int itemCount, String artist) {
        super(itemName, itemPrice, itemCount);
        this.artist = artist;
    }

    @Override
    public void changeInfo(ItemDto itemDto) {
        super.changeInfo(itemDto);
        this.artist = itemDto.getCode();
    }
}
