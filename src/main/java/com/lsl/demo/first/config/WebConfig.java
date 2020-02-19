package com.lsl.demo.first.config;

import com.lsl.demo.first.interceptor.AuthenticationInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author lisiliang
 * @since 2020/2/12
 */

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private AuthenticationInterceptor authenticationInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authenticationInterceptor).excludePathPatterns("/swagger-ui.html",
                "/swagger-resources/**", "/v2/api-docs", "/webjars/springfox-swagger-ui/**");
    }
}
