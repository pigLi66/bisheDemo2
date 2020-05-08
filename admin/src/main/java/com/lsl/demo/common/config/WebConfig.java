package com.lsl.demo.common.config;

import com.lsl.demo.common.interceptor.AuthenticationInterceptor;
import com.lsl.demo.common.interceptor.OptionMethodInterceptor;
import com.lsl.demo.common.interceptor.TestInterceptor;
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
    private TestInterceptor testInterceptor;

    public WebConfig(AuthenticationInterceptor authenticationInterceptor,
                     OptionMethodInterceptor optionMethodInterceptor,
                     TestInterceptor testInterceptor) {
        this.authenticationInterceptor = authenticationInterceptor;
        this.optionMethodInterceptor = optionMethodInterceptor;
        this.testInterceptor = testInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(optionMethodInterceptor);
        registry.addInterceptor(authenticationInterceptor).excludePathPatterns("/swagger-ui.html",
                "/swagger-resources/**", "/v2/api-docs", "/webjars/springfox-swagger-ui/**",
                "/sys/user/login", "/sys/user/register",
                "/sys/artist/**");
        registry.addInterceptor(testInterceptor);
    }
}
