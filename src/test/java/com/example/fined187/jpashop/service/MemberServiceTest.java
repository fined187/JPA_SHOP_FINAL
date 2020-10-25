package com.example.fined187.jpashop.service;

import com.example.fined187.jpashop.domain.dto.MemberDTO;
import com.example.fined187.jpashop.domain.entity.Address;
import com.example.fined187.jpashop.domain.entity.Member;
import com.example.fined187.jpashop.mapper.MemberMapper;
import com.example.fined187.jpashop.repository.MemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MemberServiceTest {

    @Mock
    private MemberRepository memberRepository;

    @Mock
    private MemberMapper memberMapper;

    @Test
    @DisplayName("회원 가입 테스트")
    void joinTest() throws Exception {
        MemberService memberService = new MemberService(memberRepository, memberMapper);


        Address address = Address.builder()
                .city("city")
                .street("street")
                .zipcode("zipcode")
                .build();
//      given
        Member member = Member.builder()
                .id(1l)
                .name("wtl")
                .email("aaa@naver.com")
                .address(address)
                .build();

        MemberDTO memberDTO = MemberDTO.builder()
                .name("wtl")
                .email("aaa@naver.com")
                .city("city")
                .street("street")
                .zipcode("zipcode")
                .build();

        Member saveMember = Member.builder()
                .id(1L)
                .name("wtl")
                .email("aaa@naver.com")
                .address(address)
                .build();

//      when
        when(memberRepository.save(member)).thenReturn(saveMember);
        when(memberMapper.toEntity(memberDTO)).thenReturn(member);

//      when 실행
        Long memberId = memberService.join(memberDTO);

//      then
        assertEquals(1L, memberId);
    }
}
