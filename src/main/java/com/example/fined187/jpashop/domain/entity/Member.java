package com.example.jpashop.domain.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Member {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    @OneToMany(mappedBy = "member")
    List<com.example.jpashop.domain.entity.Order> orders = new ArrayList<>();

    @Column(name = "member_name")
    private String name;
    private String email;

    @Embedded
    private com.example.jpashop.domain.entity.Address address;

    @Builder
    public Member(Long id, String name, String email, com.example.jpashop.domain.entity.Address address) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.address = address;
    }

    public void changeMemberName(String name) {
        this.name = name;
    }

}
