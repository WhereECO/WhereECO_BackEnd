package com.whereeco.controller.api.login.dto;

import lombok.Builder;
import lombok.Getter;

public class TokenDto {

    @Getter
    public static class Request{
        private String userId;
        private String pwd;
    }

    @Getter @Builder
    public static class Response{
        private String grantType;
        private String accessToken;
    }
}

























