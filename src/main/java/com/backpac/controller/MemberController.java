package com.backpac.controller;

import com.backpac.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("api/members")
public class MemberController {

    /**
     * 단건조회
     * @param id
     * @return
     */
    @GetMapping(path = "{id}")
    public String getMember(@PathVariable("id") Long id) {
        return "hello";
    }

}
