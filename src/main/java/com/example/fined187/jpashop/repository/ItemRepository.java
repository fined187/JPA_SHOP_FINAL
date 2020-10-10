package com.example.fined187.jpashop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<com.example.jpashop.domain.entity.item.Item, Long> {
}
