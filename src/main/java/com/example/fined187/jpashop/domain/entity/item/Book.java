package com.example.fined187.jpashop.domain.entity.item;

import com.example.fined187.jpashop.domain.dto.ItemDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@NoArgsConstructor
@Getter
@Setter
@DiscriminatorValue("book")
public class Book extends Item{
    private String bAuthor;
    private String isbn;

    @Builder
    public Book(String itemName, int itemPrice, int itemCount, String bAuthor, String isbn) {
        super(itemName, itemPrice, itemCount);
        this.bAuthor = bAuthor;
        this.isbn = isbn;
    }

//  책의 정보를 수정하는 method.
    @Override
    public void changeInfo(ItemDto itemDto) {
        super.changeInfo(itemDto);
        this.bAuthor = itemDto.getBAuthor();
        this.isbn = itemDto.getIsbn();
    }
}
