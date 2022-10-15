package com.whereeco.global.interceptor;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;

@Component
@Slf4j
@RequiredArgsConstructor
/**
 *  이미 로그인한 사용자가 로그인 화면에 접근하지 못하게 함.
 *  /user/login 접근 시 인터셉터 실행
 */
public class WebLogoutCheckInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String requestURI = request.getRequestURI();
        log.info("인터셉터 실행. requestUrl => {}", requestURI);

        HttpSession session = request.getSession(false); // false.로 해야 불필요한 세션생성이 없음

        // 서버에서 세션 속성을 등록했는지 검사하여 로그아웃 상태이면 정상적으로 로그인 화면으로 안내
        if (session==null || session.getAttribute("userId") == null){
            log.info("인증 사용자 요청. Login ");
            return true;
        }

        // 이미 로그인했으니까 map 화면으로 redirect
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<script>location.href='/user/map';</script>");
        out.flush();
        log.info("인증 사용자 요청 -> {}", session.getAttribute("userId"));
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
