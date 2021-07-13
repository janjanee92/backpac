package com.backpac.service;

import com.backpac.domain.Member;
import com.backpac.domain.UserAccount;
import com.backpac.dto.MemberRequest;
import com.backpac.dto.MemberResponse;
import com.backpac.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService implements UserDetailsService {

    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return memberRepository.findByEmail(email)
                .orElseThrow(() ->
                        new UsernameNotFoundException(email));
    }

    @Transactional
    public Long saveNewMember(@Valid MemberRequest memberRequest) {
        boolean memberExist = memberRepository.findByEmail(memberRequest.getEmail())
                .isPresent();
        if (memberExist) {
            throw new IllegalStateException("이미 존재하는 이메일 주소입니다.");
        }

        Member newMember = Member.builder()
                .name(memberRequest.getName())
                .email(memberRequest.getEmail())
                .gender(memberRequest.getGender())
                .nickname(memberRequest.getNickname())
                .password(bCryptPasswordEncoder.encode(memberRequest.getPassword()))
                .phoneNumber(memberRequest.getPhoneNumber())
                .build();

        return memberRepository.save(newMember).getId();
    }

    private void login(Member member) {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                new UserAccount(member),
                member.getPassword(),
                member.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(token);
    }

    public MemberResponse login(MemberRequest memberRequest) {
        Member member = memberRepository.findByEmail(memberRequest.getEmail())
                .orElseThrow(() ->
                        new IllegalStateException("로그인에 실패했습니다."));
        login(member);

        return MemberResponse.builder()
                .id(member.getId())
                .name(member.getName())
                .nickname(member.getNickname())
                .email(member.getEmail())
                .build();
    }
}
