package com.example.fined187.jpashop.controller;

import com.example.fined187.jpashop.domain.entity.Address;
import com.example.fined187.jpashop.domain.entity.Member;
import com.example.fined187.jpashop.service.MemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(MemberController.class)
public class MemberControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private MemberService memberService;

    Member member = Member.builder()
            .name(memberService.getMember(1L).toString())
            .email("fined000@nvaer.com")
            .address(Address.builder()
                    .city("city1")
                    .street("street1")
                    .zipcode("zipcode1")
                    .build())
            .build();

    @Test
    @DisplayName("회원 가입")
    void joinTest() throws Exception {
        BDDMockito.given(memberService.join(any())).willReturn(1L);
        mvc.perform(post("/api/v1/members")
        .contentType("{\"name\" : \"lwt\", \"email\" : \"find000@naver.com\",\"street\" : \"street1\",\"city\" : \"city1\",\"zipcode\" : \"zipcode\"}"))
                .andExpect(status().isCreated())
                .andExpect(header().string("location", "http://localhost/api/v1/members/1"));
        then(memberService).should(times(1)).join(any());
    }

    @Test
    @DisplayName("회원 조회")
    void getMemberTest() throws Exception {
//        given
        given(memberService.getMember(1L));

        mvc.perform(get("/api/v1/members"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(header().string("location", "http://localhost/api/v1/members"));

        //  then
        then(memberService).should(times(1)).notify();
    }
}
