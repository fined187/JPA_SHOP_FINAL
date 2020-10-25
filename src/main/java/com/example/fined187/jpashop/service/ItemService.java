package com.example.fined187.jpashop.service;


import com.example.fined187.jpashop.domain.dto.ItemDTO;

import java.util.List;

public interface ItemService {
    public ItemDTO getItem(Long id);
    public List<ItemDTO> getItemList();
    public Long registerItem(ItemDTO itemDTO);
    public void update(Long id, ItemDTO itemDTO);
}
