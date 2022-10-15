package com.whereeco.global.interceptor;

import com.whereeco.domain.jwt.constant.GrantType;
import com.whereeco.domain.jwt.constant.TokenType;
import com.whereeco.domain.jwt.service.TokenProvider;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

@Component
@Slf4j
@RequiredArgsConstructor
public class TokenCheckInterceptor implements HandlerInterceptor {

    private final TokenProvider tokenProvider;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        //  1. authorization 헤더가 있는지 체크
        String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (!StringUtils.hasText(authorizationHeader)){
            throw new RuntimeException("authorization 헤더 아님");
        }

        //  2. authorization Header의 TokenType Bearer 체크
        String[] authorizations = authorizationHeader.split(" ");
        if( authorizations.length < 2 || ( !GrantType.BEARER.getType().equals(authorizations[0]))){
            throw new RuntimeException("BEARER 토큰 아님");
        }

        //  3. 토큰 검증
        String token = authorizations[1];   // token 변수는 액세스 토큰의 몸통 부분.
        if( !tokenProvider.validateToken(token)){
            throw new RuntimeException("토큰 값 오류");
        }

        //  4. 토큰 타입 검증. ACCESS or REFRESH
        String tokenType = tokenProvider.getTokenType(token);
        if( !TokenType.ACCESS.name().equals(tokenType)){
            throw new RuntimeException("토큰타입 ACCESS 아님");
        }

        //  5. 액세스 토큰 만료 시작 검증
        // 일단 isTokenExpired() 인자로 넣을 토큰만료일 Date 객체를 가져옴
        Claims tokenClaims = tokenProvider.getTokenClaims(token);
        Date expiration = tokenClaims.getExpiration();

        if (tokenProvider.isTokenExpired(expiration)) {
            throw new RuntimeException("토큰 만료됨");
        }

        log.info("token Audience =>  {}", tokenClaims.getAudience());
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
