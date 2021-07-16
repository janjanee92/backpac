package com.backpac.module.member.dto;

import com.backpac.module.member.entity.Gender;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SignUpDto {

    //TODO: 한글, 영문대소문자
    @NotBlank
    @Length(max = 20)
    private String name;

    // TODO: 영문 소문자
    @NotBlank
    @Length(max = 30)
    private String nickname;

    @NotBlank
    private String phoneNumber;

    // TODO: 영문 대문자, 영문 소문자, 특수 문자, 숫자 각 1개 이상 포함
    @NotBlank
    @Length(min = 10, max = 255)
    private String password;

    @Email
    @NotBlank
    private String email;
    private Gender gender;

}