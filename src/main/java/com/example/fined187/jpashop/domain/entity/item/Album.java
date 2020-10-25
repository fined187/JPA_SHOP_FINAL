package com.example.fined187.jpashop.domain.entity.item;

import com.example.fined187.jpashop.domain.dto.ItemDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@DiscriminatorValue("album")
@NoArgsConstructor
@Getter @Setter
public class Album extends Item {
    private String artist;

    @Builder
    public Album(String itemName, int itemCount, int itemPrice, String artist) {
        super(itemName, itemCount, itemPrice);
        this.artist = artist;
    }

    @Override
    public void changeInfo(ItemDTO itemDTO) {
        super.changeInfo(itemDTO);
        this.artist = itemDTO.getArtist();
    }
}
