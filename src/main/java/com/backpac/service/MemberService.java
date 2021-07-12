package com.backpac.service;

import com.backpac.domain.Member;
import com.backpac.dto.MemberForm;
import com.backpac.mapper.MemberMapper;
import com.backpac.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    private MemberMapper mapper = Mappers.getMapper(MemberMapper.class);
    @Transactional
    public Member saveNewMember(@Valid MemberForm memberForm) {
        memberForm.setPassword(passwordEncoder.encode(memberForm.getPassword()));
        Member member = mapper.formToEntity(memberForm);
        return memberRepository.save(member);
    }
}
