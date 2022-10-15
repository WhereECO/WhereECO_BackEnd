package com.whereeco.controller.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter @Setter
public class LoginDto {

    @NotBlank
    private String userId;

    @NotBlank
    private String pwd;
}
