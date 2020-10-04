package com.example.fined187.jpashop.mapper;

import com.example.fined187.jpashop.domain.dto.MemberDto;
import com.example.fined187.jpashop.domain.entity.Address;
import com.example.fined187.jpashop.domain.entity.Member;
import org.springframework.stereotype.Component;

@Component
public class MemberMapper implements EntityMapper<Member, MemberDto>{


    @Override
    public Member toEntity(MemberDto dto) {
        Address address = Address.builder()
                .city(dto.getCity())
                .street(dto.getStreet())
                .zipcode(dto.getZipcode())
                .build();

        return Member.builder()
                .name(dto.getName())
                .address(address)
                .email(dto.getEmail())
                .build();
    }

    @Override
    public MemberDto toDto(Member entity) {
        return MemberDto.builder()
                .name(entity.getName())
                .city(entity.getAddress().getCity())
                .email(entity.getEmail())
                .street(entity.getAddress().getStreet())
                .zipcode(entity.getAddress().getZipcode())
                .build();
    }
}
