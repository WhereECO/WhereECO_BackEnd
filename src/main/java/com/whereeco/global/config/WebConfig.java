package com.whereeco.global.config;

import com.whereeco.global.interceptor.TokenCheckInterceptor;
import com.whereeco.global.interceptor.WebLoginCheckInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {

    private final WebLoginCheckInterceptor webLoginCheckInterceptor;
    private final TokenCheckInterceptor tokenCheckInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(webLoginCheckInterceptor)
                .order(0)
                .addPathPatterns("/user/map")
        ;

        registry.addInterceptor(tokenCheckInterceptor)
                .order(0)
                .addPathPatterns("/users/todo")
        ;
    }
}
