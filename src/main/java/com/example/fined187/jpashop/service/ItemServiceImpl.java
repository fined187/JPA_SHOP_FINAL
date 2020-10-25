package com.example.fined187.jpashop.service;

import com.example.fined187.jpashop.domain.dto.ItemDTO;
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
@Transactional(readOnly = true)
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemJpaRepository;
    private final ItemMapper itemMapper;

    public ItemDTO getItem(Long id) {
        Optional<Item> findItem = itemJpaRepository.findById(id);
        Item item = findItem
                .orElseThrow(() -> new NotFoundException("not found item. id : " + id));

        return itemMapper.toDto(item);
    }

    @Override
    public List<ItemDTO> getItemList() {

        List<Item> items =
                Optional.of(itemJpaRepository.findAll())
                        .orElse(Collections.emptyList());

        return items.stream()
                .map(itemMapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public Long registerItem(ItemDTO itemDTO) {

        Item item = itemMapper.toEntity(itemDTO);

        return itemJpaRepository.save(item).getId();
    }

    @Transactional
    @Override
    public void update(Long id, ItemDTO itemDTO) {
        Item item = itemJpaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("" + id));

        item.changeInfo(itemDTO);
    }
}
