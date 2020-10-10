package com.example.jpashop.controller;

import com.example.jpashop.domain.dto.ApiResponse;
import com.example.jpashop.domain.dto.ItemDTO;
import com.example.jpashop.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ItemController {
    private final ItemService itemService;

    @PostMapping("/v1/items")
    ResponseEntity<?> createItem(@RequestBody ItemDTO itemDTO) {

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(itemService.registerItem(itemDTO))
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @GetMapping("/v1/items/{id}")
    ResponseEntity<?> getItem(@PathVariable("id") Long id) {
        ItemDTO findItem = itemService.getItem(id);

//      첫 번째 방법.
//      return ResponseEntity.ok().body(findItem);

//      두 번째 방법.
        return ResponseEntity.ok().body(ApiResponse.success(findItem));
    }

    @GetMapping("/v1/items")
    ResponseEntity<?> getItems(Long id) {
        return ResponseEntity.ok().body(itemService.getItemList());
    }

    @PutMapping("/v1/items/{id}")
    ResponseEntity<ItemDTO> updateItem(
            @PathVariable("id") Long id, @RequestBody ItemDTO itemDTO) {

        itemService.update(id, itemDTO);

        return ResponseEntity.ok().body(null);
    }
}
