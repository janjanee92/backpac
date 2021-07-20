package com.backpac.module.member.controller;

import com.backpac.module.member.dto.MemberDto;
import com.backpac.module.member.dto.MemberOrderDto;
import com.backpac.module.member.dto.condition.MemberCond;
import com.backpac.module.member.entity.Member;
import com.backpac.module.member.service.MemberService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;


@RestController
@RequiredArgsConstructor
@RequestMapping("api/members")
public class MemberController {

    private final MemberService memberService;

    @ApiOperation(value = "단일 회원 조회", notes="회원 id를 입력받아 단일 회원을 조회합니다.")
    @GetMapping("{id}")
    @ApiImplicitParam(name = "id", paramType = "path", value = "회원 id (ex - 1)")
    public ResponseEntity<MemberDto> findMember(@PathVariable Long id) {
        Member findMember = memberService.findOne(id);
        MemberDto memberDto = MemberDto.entityToDto(findMember);
        return new ResponseEntity<>(memberDto, HttpStatus.OK);
    }

    @ApiOperation(value = "여러 회원 조회", notes = "이름, 이메일, 페이징 조건을 사용하여 여러회원을 조회합니다.")
    @GetMapping()
    @ApiImplicitParams({
            @ApiImplicitParam(name = "email", value = "회원 이메일"),
            @ApiImplicitParam(name = "name", value = "회원 이름"),
            @ApiImplicitParam(name = "page", dataType = "integer", paramType = "query",
                    value = "페이지 (default - 0)"),
            @ApiImplicitParam(name = "size", dataType = "integer", paramType = "query",
                    value = "페이지 레코드 갯수 (default - 20)")}
    )
    public ResponseEntity<Page<MemberOrderDto>> searchMembers(MemberCond memberCond, @ApiIgnore Pageable pageable) {
        Page<MemberOrderDto> search = memberService.findAll(memberCond, pageable);
        return new ResponseEntity<>(search, HttpStatus.OK);
    }

}
