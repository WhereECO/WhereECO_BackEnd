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

    @NotBlank
    private String userId;

    @NotBlank
    private String name;

    @NotBlank
    private String pwd1;

    @NotBlank
    private String pwd2;
}
