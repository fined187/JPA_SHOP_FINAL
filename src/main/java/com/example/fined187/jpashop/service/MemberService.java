package com.example.fined187.jpashop.service;

import com.example.fined187.jpashop.domain.dto.MemberDTO;
import com.example.fined187.jpashop.domain.entity.Member;
import com.example.fined187.jpashop.exception.NotFoundException;
import com.example.fined187.jpashop.mapper.MemberMapper;
import com.example.fined187.jpashop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {
    private final MemberRepository memberRepository;
    private final MemberMapper memberMapper;

    @Transactional
    public Long join(MemberDTO memberDTO) {
        validateDuplicateMember(memberDTO);

        Member member = memberMapper.toEntity(memberDTO);
        return memberRepository.save(member).getId();
    }

    private void validateDuplicateMember(MemberDTO memberDTO) {
        List<Member> findMembers = memberRepository.findByName(memberDTO.getName());
        if(!findMembers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    public List<MemberDTO> findMembers() {
        List<Member> members =
                Optional.of(memberRepository.findAll())
                        .orElse(Collections.emptyList());

        return members.stream()
                .map(memberMapper::toDto)
                .collect(Collectors.toList());
    }

    public MemberDTO getMember(Long id) {
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("not found member" + id));;

        return memberMapper.toDto(member);
    }

    @Transactional
    public void update(Long memberId, String name) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new NotFoundException("not found member id" + memberId));

        member.changeMemberName(name);
    }

}
