package com.example.fined187.jpashop.repository;

import com.example.fined187.jpashop.domain.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> {
    List<Member> findByName(String name);
    List<Member> findByEmail(String email);
    List<Member> findByNameAndEmail(String name, String email);
}
