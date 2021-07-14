package com.backpac.service;

import com.backpac.domain.Member;
import com.backpac.dto.SignUpDto;
import com.backpac.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
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
    public Long saveNewMember(@Valid SignUpDto signUpDto) {
        validateDuplicateMemberEmail(signUpDto.getEmail());

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

    private void validateDuplicateMemberEmail(String email) {
        boolean memberExist = memberRepository.findByEmail(email)
                .isPresent();

        if (memberExist) {
            throw new IllegalStateException("이미 존재하는 이메일 주소입니다.");
        }
    }

    public Member findOne(String emailOrNickname) {
        return memberRepository.findByEmail(emailOrNickname)
                .orElseGet(() -> memberRepository.findByNickname(emailOrNickname)
                        .orElseThrow(() -> new IllegalStateException("사용자 정보가 존재하지 않습니다.")));
    }

}
