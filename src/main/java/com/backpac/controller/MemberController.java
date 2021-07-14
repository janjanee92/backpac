package com.backpac.controller;

import com.backpac.domain.Member;
import com.backpac.dto.MemberResponseDto;
import com.backpac.repository.MemberRepository;
import com.backpac.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("api/members")
public class MemberController {

    private final MemberService memberService;

    /**
     * 단일 회원 조회
     * @param id
     * @return
     */
    @GetMapping
    public ResponseEntity<MemberResponseDto> findMember(@Param("id") String id) {
        Member findMember = memberService.findOne(id);
        MemberResponseDto memberResponseDto = MemberResponseDto.entityToDto(findMember);
        return new ResponseEntity<>(memberResponseDto, HttpStatus.OK);
    }

}
