package com.example.fined187.jpashop.repository;

import com.example.fined187.jpashop.domain.entity.item.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
