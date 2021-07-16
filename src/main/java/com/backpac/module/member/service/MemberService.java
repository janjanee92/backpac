package com.backpac.module.member.service;

import com.backpac.module.member.dto.MemberOrderDto;
import com.backpac.module.member.dto.SignUpDto;
import com.backpac.module.member.dto.condition.MemberCond;
import com.backpac.module.member.entity.Member;
import com.backpac.module.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    public static final String USER_NOT_FOUND_MSG = "사용자를 찾을 수 없습니다.";
    public static final String USER_EXIST_NICKNAME_MSG = "이미 존재하는 닉네임 입니다.";
    public static final String USER_EXIST_EMAIL_MSG = "이미 존재하는 이메일 주소입니다.";

    @Override
    public UserDetails loadUserByUsername(String emailOrNickname) throws UsernameNotFoundException {
        return memberRepository.findByEmailOrNickname(emailOrNickname, emailOrNickname)
                .orElseThrow(() -> new UsernameNotFoundException(emailOrNickname));
    }

    @Transactional
    public Long saveNewMember(@Valid SignUpDto signUpDto) {
        validateDuplicateMember(signUpDto.getEmail(), signUpDto.getNickname());

        Member newMember = Member.builder()
                .name(signUpDto.getName())
                .email(signUpDto.getEmail())
                .gender(signUpDto.getGender())
                .nickname(signUpDto.getNickname())
                .password(bCryptPasswordEncoder.encode(signUpDto.getPassword()))
                .phoneNumber(signUpDto.getPhoneNumber())
                .build();

        return memberRepository.save(newMember).getId();
    }

    private void validateDuplicateMember(String email, String nickname) {
        boolean existsByEmail = memberRepository.existsByEmail(email);

        if (existsByEmail) {
            throw new IllegalStateException(USER_EXIST_EMAIL_MSG);
        }

        boolean existsByNickname = memberRepository.existsByNickname(nickname);

        if (existsByNickname) {
            throw new IllegalStateException(USER_EXIST_NICKNAME_MSG);
        }
    }

    public Member findOne(Long id) {
        return memberRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException(USER_NOT_FOUND_MSG));
    }

    public Page<MemberOrderDto> findAll(MemberCond cond, Pageable pageable) {
        Page<Member> members = memberRepository.searchPage(cond, pageable);
        return members.map(MemberOrderDto::new);
    }

}
