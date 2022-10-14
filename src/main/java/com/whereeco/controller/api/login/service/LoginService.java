package com.whereeco.controller.api.login.service;


import com.whereeco.controller.api.login.dto.AccessTokenResponseDto;
import com.whereeco.controller.api.login.dto.TokenDto;
import com.whereeco.domain.jwt.constant.TokenType;
import com.whereeco.domain.jwt.service.TokenProvider;
import com.whereeco.domain.user.entity.User;
import com.whereeco.domain.user.service.UserService;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class LoginService {

    private final PasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;
    private final UserService userService;

    @Transactional
    public TokenDto.Response login(TokenDto.Request request) {

        //  비밀번호 일치 검사
        User user = userService.findByUserId(request.getUserId());
        if (! passwordEncoder.matches(request.getPwd(), user.getPwd())){
            throw new IllegalArgumentException("비밀번호 불일치");
        }

        TokenDto.Response tokenDto = tokenProvider.createTokenDto(user.getUserId());
        return tokenDto;
    }

    @Transactional // Member 객체의 tokenExpirationTime 을 set 해서 변경시키기 위함.
    public void logout(String accessToken) {
        //  1. access Token 만료 확인
        Claims tokenClaims = tokenProvider.getTokenClaims(accessToken);
        Date accessTokenExpiration = tokenClaims.getExpiration();
        if( tokenProvider.isTokenExpired(accessTokenExpiration)){
            throw new RuntimeException("토큰 이미 만료");
        }

        //  2. 토큰 타입 검증
        String tokenType = tokenClaims.getSubject();
        if (!TokenType.isAccessToken(tokenType)){
            throw new RuntimeException("토큰타입 Bearer 아님");
        }
    }
}

















