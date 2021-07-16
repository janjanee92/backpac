package com.backpac.module.member.dto;

import com.backpac.module.member.entity.Gender;
import com.backpac.module.member.entity.Member;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MemberDto {
    private Long id;
    private String name;
    private String nickname;
    private String email;
    private String phoneNumber;
    private Gender gender;

    public static MemberDto entityToDto(Member member) {
        return MemberDto.builder()
                .id(member.getId())
                .name(member.getName())
                .nickname(member.getNickname())
                .email(member.getEmail())
                .phoneNumber(member.getPhoneNumber())
                .gender(member.getGender())
                .build();
    }
}
