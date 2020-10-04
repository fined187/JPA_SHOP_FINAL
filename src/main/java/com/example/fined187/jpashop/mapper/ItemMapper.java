package com.example.fined187.jpashop.mapper;

import com.example.fined187.jpashop.domain.dto.ItemDto;
import com.example.fined187.jpashop.domain.entity.item.Album;
import com.example.fined187.jpashop.domain.entity.item.Book;
import com.example.fined187.jpashop.domain.entity.item.Item;
import com.example.fined187.jpashop.domain.entity.item.Laptop;
import org.mapstruct.Mapper;

import java.util.Arrays;

@Mapper(componentModel = "spring")
public class ItemMapper implements EntityMapper<Item, ItemDto> {
    @Override
    public Item toEntity(ItemDto dto) {
        ItemEnumFactory itemEnumFactory = Arrays.stream(ItemEnumFactory.values())
                .filter(factory -> factory.getType().equals(dto.getType()))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);

        return itemEnumFactory.create(dto);
    }

    @Override
    public ItemDto toDto(Item entity) {
        ItemDto itemDto = new ItemDto();
        itemDto.setItemName(entity.getItemName());
        itemDto.setItemPrice(entity.getItemPrice());
        itemDto.setItemCount(entity.getItemCount());

        if(entity instanceof Book) {
            itemDto.setBAuthor(((Book) entity).getBAuthor());
            itemDto.setIsbn(((Book) entity).getIsbn());

        }else if(entity instanceof Laptop) {
            itemDto.setCode(((Laptop) entity).getCode());
        } else (entity instanceof Album) {
            itemDto.setArtist(entity.getArtist);
        }
        return null;
    }
}
