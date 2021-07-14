package com.backpac.dto;

import com.backpac.domain.Gender;
import com.backpac.domain.Member;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MemberResponseDto {
    private Long id;
    private String name;
    private String nickname;
    private String email;
    private String phoneNumber;
    private Gender gender;

    public static MemberResponseDto entityToDto(Member member) {
        return MemberResponseDto.builder()
                .id(member.getId())
                .name(member.getName())
                .nickname(member.getNickname())
                .email(member.getEmail())
                .phoneNumber(member.getPhoneNumber())
                .gender(member.getGender())
                .build();
    }
}
