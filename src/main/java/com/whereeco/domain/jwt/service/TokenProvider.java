package com.whereeco.domain.jwt.service;

import com.whereeco.controller.api.login.dto.TokenDto;
import com.whereeco.domain.jwt.constant.GrantType;
import com.whereeco.domain.jwt.constant.TokenType;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Slf4j
public class TokenProvider {

    private String accessTokenExpirationTime = "6000000"; // 100분

    private String tokenSecret = "NMA8JPctFuna59f5";

    public TokenDto.Response createTokenDto(String userId){
        Date accessTokenExpireTime = createAccessTokenExpireTime();

        String accessToken = createAccessToken(userId, accessTokenExpireTime);

        return TokenDto.Response.builder()
                .grantType(GrantType.BEARER.getType())
                .accessToken(accessToken)
                .build();
    }

    public String createAccessToken(String userId,  Date expirationTime) {
        return Jwts.builder()
                .setSubject(TokenType.ACCESS.name())    // 토큰 제목 ACCESS
                .setAudience(userId)                 //  토큰에 담을 개인정보(대상자)
                .setIssuedAt(new Date())        //  발급시간
                .setExpiration(expirationTime)  //
                .signWith(SignatureAlgorithm.HS512, tokenSecret)    // 시그니처를 만들 알고리즘과 시크릿키 값
                .setHeaderParam("typ", "JWT")
                .compact();
    }

    public Date createAccessTokenExpireTime() {
        return new Date(System.currentTimeMillis() + Long.parseLong(accessTokenExpirationTime));
    }

    public Claims getTokenClaims(String accessToken) {
        Claims claims;
        try {
            claims = Jwts.parser().setSigningKey(tokenSecret)
                    .parseClaimsJws(accessToken).getBody();
        } catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("getTokenCliams 예외");
        }
        return claims;
    }

    public boolean isTokenExpired(Date accessTokenExpiredTime) {
        Date now = new Date();
        if(now.after(accessTokenExpiredTime)){
            return true;
        }
        return false;
    }

    public boolean validateToken(String token) {
        try {
            // access Token 을 검증만 하는 것이기 때문에, getBody()로 Claim 을 가져와 무언가를 하진 않는다.
            Jwts.parser().setSigningKey(tokenSecret)
                    .parseClaimsJws(token);
            return true;
        } catch (JwtException e){   //  토큰 변조
            e.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    public String getTokenType(String token) {
        String tokenType;
        try {
            Claims claims = Jwts.parser().setSigningKey(tokenSecret)
                    .parseClaimsJws(token).getBody();
            tokenType = claims.getSubject();
        } catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("getTokenType 예외");
        }
        return tokenType;
    }
}

























