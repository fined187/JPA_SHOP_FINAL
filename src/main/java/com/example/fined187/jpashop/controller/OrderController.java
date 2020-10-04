package com.example.fined187.jpashop.controller;

import com.example.fined187.jpashop.domain.dto.OrderDto;

import com.example.fined187.jpashop.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class OrderController {
    private final OrderService orderService;

    @PostMapping("/v1/orders")
    ResponseEntity<?> order(@RequestBody OrderDto orderDto) {
        orderService.order(orderDto);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(orderService.order(orderDto))
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @PutMapping("/v1/orders/{id}")
    ResponseEntity<?> cancel(@PathVariable("id") Long id) {
        orderService.cancel(id);

        return ResponseEntity.ok().body("{}");
    }

    @GetMapping("/v1/orders/{id}")
    ResponseEntity<?> getOrder(@PathVariable("id") Long id) {

        return ResponseEntity.ok().body(orderService.getOrder(id));
    }

    @GetMapping("/v1/orders")
    ResponseEntity<?> getOrder() {
        return ResponseEntity.ok().body(orderService.getOrders());
    }
}
