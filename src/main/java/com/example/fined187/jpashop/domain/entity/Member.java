package com.example.fined187.jpashop.domain.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
public class Member {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    @OneToMany(mappedBy = "member")
    List<Order> orders = new ArrayList<>();

    @Column(name = "member_name")
    private String name;
    private String email;

    @Embedded
    private Address address;

    @Builder
    public Member(String name, String email, Address address) {
        this.name = name;
        this.email = email;
        this.address = address;
    }

//  이름을 바꾸는 편의 method.
    public void changeMemberName(String name) {
        this.name = name;
    }
}
