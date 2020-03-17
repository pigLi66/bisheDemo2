package com.lsl.demo.common.config;

import com.lsl.demo.common.interceptor.AuthenticationInterceptor;
import com.lsl.demo.common.interceptor.OptionMethodInterceptor;
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

    private AuthenticationInterceptor authenticationInterceptor;

    private OptionMethodInterceptor optionMethodInterceptor;

    public WebConfig(AuthenticationInterceptor authenticationInterceptor,
                     OptionMethodInterceptor optionMethodInterceptor) {
        this.authenticationInterceptor = authenticationInterceptor;
        this.optionMethodInterceptor = optionMethodInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(optionMethodInterceptor);
        registry.addInterceptor(authenticationInterceptor).excludePathPatterns("/swagger-ui.html",
                "/swagger-resources/**", "/v2/api-docs", "/webjars/springfox-swagger-ui/**",
                "/sys/user/login", "/sys/user/register",
                "/sys/artist/**");
    }
}
