package com.whereeco.global.config;

import com.whereeco.global.interceptor.TokenCheckInterceptor;
import com.whereeco.global.interceptor.WebLoginCheckInterceptor;
import com.whereeco.global.interceptor.WebLogoutCheckInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {

    private final WebLoginCheckInterceptor webLoginCheckInterceptor;
    private final WebLogoutCheckInterceptor webLogoutCheckInterceptor;
    private final TokenCheckInterceptor tokenCheckInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(webLoginCheckInterceptor)
                .order(0)
                .addPathPatterns("/user/map")
        ;

        registry.addInterceptor(webLogoutCheckInterceptor)
                .order(0)
                .addPathPatterns("/user/login")
        ;

        registry.addInterceptor(tokenCheckInterceptor)
                .order(0)
                .addPathPatterns("/users/todo")
        ;
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // 이 경로로 접근하는
                .allowedOrigins("*") // 해당 Origin에 대해 CORS 활성화
                .allowedMethods(
                        HttpMethod.GET.name()
                        , HttpMethod.POST.name()
                        , HttpMethod.PUT.name()
                        , HttpMethod.PATCH.name()
                        , HttpMethod.DELETE.name()
                        , HttpMethod.OPTIONS.name()
                );
    }
}
