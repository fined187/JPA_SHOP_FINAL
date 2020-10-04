package com.example.fined187.jpashop.converter;

import com.example.fined187.jpashop.domain.dto.MemberDto;
import com.example.fined187.jpashop.domain.entity.Member;

public class MemberConverter {
    public static MemberDto toMemberDto(Member member) {
        return MemberDto.builder()
                .name(member.getName())
                .email(member.getEmail())
                .street(member.getAddress().getStreet())
                .zipcode(member.getAddress().getZipcode())
                .city(member.getAddress().getZipcode())
                .build();
    }
}
