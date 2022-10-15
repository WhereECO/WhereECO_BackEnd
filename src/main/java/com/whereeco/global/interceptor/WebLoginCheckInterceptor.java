package com.whereeco.global.interceptor;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.util.Date;

@Component
@Slf4j
@RequiredArgsConstructor
public class WebLoginCheckInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String requestURI = request.getRequestURI();
        log.info("인터셉터 실행. requestUrl => {}", requestURI);

        HttpSession session = request.getSession(false); // false.로 해야 불필요한 세션생성이 없음

        // 서버에서 세션 속성을 등록하지 않았거나 제거당한(invalidate 로그아웃) 상태면 접근 불가
        if (session==null || session.getAttribute("userId") == null){
            response.setContentType("text/html; charset=UTF-8");
            PrintWriter out = response.getWriter();

            log.info("미인증 사용자 요청");
            // 홈으로 redirect
            out.println("<script> alert('로그인 후 접근 가능합니다'); location.href='/user/login';</script>");
            out.flush();
            return false;
        }

        log.info("인증 사용자 요청 -> {}", session.getAttribute("userId"));
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
