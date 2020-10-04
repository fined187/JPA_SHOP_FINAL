package com.example.fined187.jpashop.controller;

import com.example.fined187.jpashop.domain.dto.ApiResponse;
import com.example.fined187.jpashop.domain.dto.ItemDto;
import com.example.fined187.jpashop.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.net.URISyntaxException;

@Controller
@RequestMapping("/api")
@RequiredArgsConstructor
public class ItemController {
    private final ItemService itemService;

    @PostMapping("/v1/items")
    ResponseEntity<?> createItem(@RequestBody ItemDto itemDto) throws URISyntaxException {
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(itemService.registerItem(itemDto))
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @GetMapping("/v1/items/{id}")
    ResponseEntity<?> getItem(@PathVariable("id") Long id) {
        ItemDto findItem = itemService.getItem(id);

        return ResponseEntity.ok().body(ApiResponse.success(findItem));
    }

    @GetMapping("/v1/items")
    ResponseEntity<?> getItems(Long id) {
        return ResponseEntity.ok().body(itemService.getItemList());
    }

    @PutMapping("v1/items/{id}")
    ResponseEntity<ItemDto> updateItem(@PathVariable("id") Long id, @RequestBody ItemDto itemDto) {

        itemService.update(id, itemDto);

        return ResponseEntity.ok().body(null);
    }
}
