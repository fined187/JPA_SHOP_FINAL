package com.example.fined187.jpashop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberRepository extends JpaRepository<com.example.jpashop.domain.entity.Member, Long> {
    List<com.example.jpashop.domain.entity.Member> findByName(String name);
    List<com.example.jpashop.domain.entity.Member> findByEmail(String email);
    List<com.example.jpashop.domain.entity.Member> findByNameAndEmail(String name, String email);
}
