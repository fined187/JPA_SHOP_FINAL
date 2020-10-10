package com.example.jpashop.mapper;

import com.example.fined187.jpashop.domain.dto.MemberDTO;
import com.example.fined187.jpashop.mapper.EntityMapper;
import com.example.jpashop.domain.entity.Address;
import com.example.jpashop.domain.entity.Member;
import org.springframework.stereotype.Component;

@Component
public class MemberMapper implements EntityMapper<Member, MemberDTO> {


    @Override
    public Member toEntity(MemberDTO dto) {

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
    public MemberDTO toDto(Member entity) {
        return MemberDTO.builder()
                .name(entity.getName())
                .city(entity.getAddress().getCity())
                .email(entity.getEmail())
                .street(entity.getAddress().getStreet())
                .zipcode(entity.getAddress().getZipcode())
                .build();
    }
}