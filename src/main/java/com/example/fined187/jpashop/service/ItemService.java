package com.example.fined187.jpashop.service;


import com.example.fined187.jpashop.domain.dto.ItemDto;
import com.example.fined187.jpashop.domain.entity.item.Item;

import java.util.List;

public interface ItemService {
    public ItemDto getItem(Long id);
    public List<ItemDto> getItemList();
    public Long registerItem(ItemDto itemDto);
    public void update(Long id, ItemDto itemDto);
}
