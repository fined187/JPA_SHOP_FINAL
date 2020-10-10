package com.example.jpashop.mapper;

import com.example.fined187.jpashop.domain.dto.ItemDTO;
import com.example.fined187.jpashop.mapper.EntityMapper;
import com.example.jpashop.domain.entity.item.Book;
import com.example.jpashop.domain.entity.item.Item;
import com.example.jpashop.domain.entity.item.Laptop;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class ItemMapper implements EntityMapper<Item, ItemDTO> {

    @Override
    public Item toEntity(ItemDTO dto) {

        com.example.jpashop.mapper.ItemEnumFactory itemFactory = Arrays.stream(com.example.jpashop.mapper.ItemEnumFactory.values())
                .filter(factory -> factory.getType().equals(dto.getType()))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);

        return itemFactory.create(dto);
    }

    @Override
    public ItemDTO toDto(Item entity) {
        ItemDTO itemDTO = new ItemDTO();
        itemDTO.setItemName(entity.getItemName());
        itemDTO.setItemCount(entity.getItemCount());
        itemDTO.setItemPrice(entity.getItemPrice());

        if(entity instanceof Book) {
            itemDTO.setBAuthor(((Book) entity).getBAuthor());
            itemDTO.setIsbn(((Book) entity).getIsbn());

        }else if(entity instanceof Laptop) {
            itemDTO.setCode(((Laptop) entity).getCode());
        }
        return itemDTO;
    }
}
