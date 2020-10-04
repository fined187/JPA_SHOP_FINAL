package com.example.fined187.jpashop.service;

import com.example.fined187.jpashop.domain.dto.ItemDto;
import com.example.fined187.jpashop.domain.entity.item.Item;
import com.example.fined187.jpashop.exception.NotFoundException;
import com.example.fined187.jpashop.mapper.ItemMapper;
import com.example.fined187.jpashop.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional                  //  update 위해서...
public class ItemServiceImpl implements ItemService{

    private final ItemRepository itemRepository;
    private final ItemMapper itemMapper;

//  Id를 이용해서 item을 조회.
    @Override
    public ItemDto getItem(Long id) {
        Optional<Item> findItem = itemRepository.findById(id);
        Item item = findItem
                .orElseThrow(() -> new NotFoundException("Not found item. id : " + id));
        return itemMapper.toDto(item);
    }

//  모든 Item을 조회하여 가져오기.
    @Override
    public List<ItemDto> getItemList() {

//      데이터베이스 조회 시 데이터가 없을 경우 null 반환 처리.
        List<Item> items = Optional.of(itemRepository.findAll())
                .orElse(Collections.emptyList());

        return items.stream()
                .map(itemMapper::toDto)
                .collect(Collectors.toList());
    }

    //  Item 등록
    @Transactional
    @Override
    public Long registerItem(ItemDto itemDto) {
        Item item = itemMapper.toEntity(itemDto);

        return itemRepository.save(item).getId();
    }

    @Transactional
    @Override
    public void update(Long id, ItemDto itemDto) {
        Item item = itemRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Not Found id: " + id));

        item.changeInfo(itemDto);
    }
}
