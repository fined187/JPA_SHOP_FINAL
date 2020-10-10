package com.example.jpashop.controller;

import com.example.jpashop.domain.dto.MemberDTO;
import com.example.jpashop.mapper.MemberMapper;
import com.example.jpashop.service.MemberService;
import lombok.RequiredArgsConstructor;
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
    ResponseEntity<MemberDTO> getMember(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok()
                .body(memberService.getMember(id));
    }

    @PostMapping("/v1/members")
    ResponseEntity<?> createMember(@RequestBody @Valid MemberDTO memberDTO) {

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(memberService.join(memberDTO))
                .toUri();

        System.out.println(location.toString());

        return ResponseEntity.created(location).build();
    }

    @GetMapping("/v1/members")
    ResponseEntity<List<MemberDTO>> getMember() {
        return ResponseEntity.ok().body(null);
    }
}
