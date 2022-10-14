package com.whereeco.controller.api.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserJoinDto {

    private String userId;

    private String name;

    private String pwd;

    private String checkPwd;
}