package com.example.fined187.jpashop.mapper;

import com.example.fined187.jpashop.domain.dto.ItemDto;
import com.example.fined187.jpashop.domain.entity.item.Album;
import com.example.fined187.jpashop.domain.entity.item.Book;
import com.example.fined187.jpashop.domain.entity.item.Item;
import com.example.fined187.jpashop.domain.entity.item.Laptop;
import lombok.Getter;

import java.util.Arrays;

public enum ItemEnumFactory {
    BOOK("book") {
        protected Item create(ItemDto itemDto) {
            return Book.builder()
                    .itemName(itemDto.getItemName())
                    .itemCount(itemDto.getItemCount())
                    .itemPrice(itemDto.getItemPrice())
                    .isbn(itemDto.getIsbn())
                    .bAuthor(itemDto.getBAuthor())
                    .build();
        }
    },

    AlBUM("album") {
        @Override
        protected Item create(ItemDto itemDto) {
            return Album.builder()
                    .itemName(itemDto.getItemName())
                    .itemCount(itemDto.getItemCount())
                    .itemPrice(itemDto.getItemPrice())
                    .artist(itemDto.getArtist())
                    .build();
        }
    },

    LAPTOP("laptop") {
        @Override
        protected Item create(ItemDto itemDto) {
            return Laptop.builder()
                    .itemName(itemDto.getItemName())
                    .itemCount(itemDto.getItemCount())
                    .itemPrice(itemDto.getItemPrice())
                    .code(itemDto.getCode())
                    .build();
        }
    };


    @Getter
    private final String type;

    ItemEnumFactory(String type) {
        this.type = type;
    }

    abstract protected Item create(ItemDto itemDto);

    public static Item createItem(ItemDto itemDto) {
        ItemEnumFactory itemEnumFactory = Arrays.stream(ItemEnumFactory.values())
                .filter(factory -> factory.type.equals(itemDto.getType()))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);

        return itemEnumFactory.create(itemDto);
    }
}

