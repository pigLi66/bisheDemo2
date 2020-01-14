package com.lsl.demo.first.config;

import com.lsl.demo.first.interceptor.AuthenticationInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 * @author lisiliang
 * @since 2020/1/11
 */
@Configuration
@EnableWebMvc
public class WebConfig extends WebMvcConfigurationSupport {

    @Autowired
    private AuthenticationInterceptor authenticationInterceptor;

    // 添加拦截器，并取消拦截swagger
    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
       /* registry.addInterceptor(authenticationInterceptor).excludePathPatterns("/swagger-ui.html",
                "/user/*");*/
    }

/*    @Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("");
        viewResolver.setSuffix(".html");
        return viewResolver;
    }*/

}
