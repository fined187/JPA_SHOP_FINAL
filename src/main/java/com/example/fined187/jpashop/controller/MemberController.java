package com.example.fined187.jpashop.controller;

import com.example.fined187.jpashop.domain.dto.MemberDto;
import com.example.fined187.jpashop.domain.entity.Member;
import com.example.fined187.jpashop.mapper.MemberMapper;
import com.example.fined187.jpashop.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;
    private final MemberMapper memberMapper;


    @GetMapping("/v1/members/{id}")
    ResponseEntity<MemberDto> getMember(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok()
                .body(memberMapper.toDto(memberService.findMember(id)));
    }

    @PostMapping("/v1/members")
    ResponseEntity<?> createMember(@RequestBody @Valid MemberDto memberDto) {
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(memberService.join(memberDto))
                .toUri();

        Long memberId = memberService.join(memberDto).getId();

        URI uri = WebMvcLinkBuilder.linkTo(MemberController.class).slash(memberId).toUri();

        System.out.println(uri.toString());

        return ResponseEntity.created(uri).body("{}");
    }

    @GetMapping("/v1/members")
    ResponseEntity<List<Member>> getMember() {
        return ResponseEntity.ok().body(null);
    }
}
