package com.backpac.module.member.controller;

import com.backpac.module.member.dto.MemberDto;
import com.backpac.module.member.dto.MemberOrderDto;
import com.backpac.module.member.dto.condition.MemberCond;
import com.backpac.module.member.entity.Member;
import com.backpac.module.member.repository.MemberRepository;
import com.backpac.module.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("api/members")
public class MemberController {

    private final MemberService memberService;
    private final MemberRepository memberRepository;

    /**
     * 단일 회원 조회
     */
    @GetMapping("{id}")
    public ResponseEntity<MemberDto> findMember(@PathVariable Long id) {
        Member findMember = memberService.findOne(id);
        MemberDto memberDto = MemberDto.entityToDto(findMember);
        return new ResponseEntity<>(memberDto, HttpStatus.OK);
    }

    /**
     * 여러 회원 조회
     */
    @GetMapping()
    public ResponseEntity<Page<MemberOrderDto>> searchMembers(MemberCond memberCond, Pageable pageable) {
        Page<MemberOrderDto> search = memberService.findAll(memberCond, pageable);
        return new ResponseEntity<>(search, HttpStatus.OK);
    }

}
