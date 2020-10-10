package com.example.jpashop.mapper;

import com.example.fined187.jpashop.domain.dto.ItemDTO;
import com.example.jpashop.domain.entity.item.Album;
import com.example.jpashop.domain.entity.item.Book;
import com.example.jpashop.domain.entity.item.Item;
import com.example.jpashop.domain.entity.item.Laptop;
import lombok.Getter;

public enum ItemEnumFactory {       //  itemDto -> entity
    BOOK("book") {
        protected Item create(ItemDTO itemVO) {
            return Book.builder()
                    .itemName(itemVO.getItemName())
                    .itemPrice(itemVO.getItemPrice())
                    .itemCount(itemVO.getItemCount())
                    .isbn(itemVO.getIsbn())
                    .bAuthor(itemVO.getBAuthor())
                    .build();
        }
    },
    ALBUM("album") {
        @Override
        protected Item create(ItemDTO itemVO) {
            return Album.builder()
                    .itemName(itemVO.getItemName())
                    .itemCount(itemVO.getItemCount())
                    .itemPrice(itemVO.getItemPrice())
                    .artist(itemVO.getArtist())
                    .build();
        }
    },
    LAPTOP("laptop") {
        @Override
        protected Item create(ItemDTO itemVO) {
            return Laptop.builder()
                    .itemName(itemVO.getItemName())
                    .itemCount(itemVO.getItemCount())
                    .itemPrice(itemVO.getItemPrice())
                    .code(itemVO.getCode())
                    .build();

        }
    };

    @Getter
    private final String type;

    ItemEnumFactory(String type) {
        this.type = type;
    }

    abstract protected Item create(ItemDTO itemVO);

//      ???

}
