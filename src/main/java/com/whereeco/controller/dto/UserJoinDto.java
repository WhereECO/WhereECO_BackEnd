package com.whereeco.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Getter @Setter
public class UserJoinDto {

    @NotBlank(message = "ID를 입력해 주세요")
    private String userId;

    @NotBlank(message = "이름을 입력해 주세요")
    private String name;

    @NotBlank(message = "비밀번호를 입력해 주세요")
    private String pwd1;

    @NotBlank(message = "비밀번호 확인을 입력해 주세요")
    private String pwd2;

}
