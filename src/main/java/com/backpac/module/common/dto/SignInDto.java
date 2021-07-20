package com.backpac.module.common.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class SignInDto {
    @ApiModelProperty(example = "backpac", required = true)
    private String username;

    @ApiModelProperty(example = "HelloWorld!@", required = true)
    private String password;
}
