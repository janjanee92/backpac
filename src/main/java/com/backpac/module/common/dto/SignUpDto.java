package com.backpac.module.common.dto;

import com.backpac.module.member.entity.Gender;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SignUpDto {

    @NotBlank
    @Length(max = 20)
    @ApiModelProperty(example = "백패커", required = true)
    private String name;

    @NotBlank
    @Length(max = 30)
    @ApiModelProperty(example = "backpac", required = true)
    private String nickname;

    @NotBlank
    @ApiModelProperty(example = "01059598282", required = true)
    private String phoneNumber;

    @NotBlank
    @Length(min = 10, max = 255)
    @ApiModelProperty(example = "HelloWorld!@", required = true)
    private String password;

    @Email
    @NotBlank
    @ApiModelProperty(example = "backpac@ker.com", required = true)
    private String email;

    @ApiModelProperty(example = "F")
    private Gender gender;

}
