package com.example.fined187.jpashop.domain.entity.item;

import com.example.fined187.jpashop.domain.dto.ItemDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
@Setter
@DiscriminatorValue("book")
public class Book extends Item {
    private String bAuthor;
    private String isbn;

    @Builder
    public Book(String itemName, int itemCount, int itemPrice, String bAuthor, String isbn) {
        super(itemName, itemCount, itemPrice);
        this.bAuthor = bAuthor;
        this.isbn = isbn;
    }

    @Override
    public void changeInfo(ItemDTO itemDTO) {
        super.changeInfo(itemDTO);
        this.bAuthor = itemDTO.getBAuthor();
        this.isbn = itemDTO.getIsbn();
    }
}
