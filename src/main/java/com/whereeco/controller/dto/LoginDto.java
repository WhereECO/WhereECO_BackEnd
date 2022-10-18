package com.whereeco.controller.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter @Setter
public class LoginDto {

    @NotBlank(message = "ID를 입력해 주세요")
    private String userId;

    @NotBlank(message = "비밀번호를 입력해 주세요")
    private String pwd;
}
