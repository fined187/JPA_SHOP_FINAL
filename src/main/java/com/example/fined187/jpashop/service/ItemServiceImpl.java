package com.example.jpashop.service;

import com.example.jpashop.domain.dto.ItemDTO;
import com.example.jpashop.domain.entity.item.Item;
import com.example.jpashop.exception.NotFoundException;
import com.example.jpashop.mapper.ItemMapper;
import com.example.jpashop.repository.ItemRepository;
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
public class ItemServiceImpl implements com.example.jpashop.service.ItemService {

    private final ItemRepository itemJpaRepository;
    private final ItemMapper itemMapper;

    //  id를 이용하여 Item 조회.(item 1개 가져옴)
    public ItemDTO getItem(Long id) {
        Optional<Item> findItem = itemJpaRepository.findById(id);
        Item item = findItem
                .orElseThrow(() -> new NotFoundException("not found item. id : " + id));

        return itemMapper.toDto(item);


//        return itemJpaRepository.findById(id)
//                .orElseThrow(() -> new NotFoundException("not found item. id : " + id));
    }

    @Override
    public List<ItemDTO> getItemList() {


//      데이터베이스 조회 시 데이터가 없을 경우 null 반환 예외처리...
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
