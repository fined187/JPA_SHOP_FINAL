package com.example.jpashop.service;

import com.example.jpashop.domain.dto.ItemDTO;
import com.example.jpashop.domain.entity.item.Item;

import java.util.List;

public interface ItemService {
    public ItemDTO getItem(Long id);
    public List<ItemDTO> getItemList();
    public Long registerItem(ItemDTO itemDTO);
    public void update(Long id, ItemDTO itemDTO);
}
