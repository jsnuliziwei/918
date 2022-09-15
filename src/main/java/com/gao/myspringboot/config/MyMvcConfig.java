package com.gao.myspringboot.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MyMvcConfig implements WebMvcConfigurer {
    //跳转页面

    /**
     * 不进行重定向,只是转发到视图
     * @param registry
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("index");
        registry.addViewController("/index.html").setViewName("index");
        registry.addViewController("/user/login.html").setViewName("index");
        registry.addViewController("/main.html").setViewName("main");
        registry.addViewController("/register.html").setViewName("user/register/page-register");
    }
    //拦截所有，放行部分路径
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        WebMvcConfigurer.super.addInterceptors(registry);

        registry.addInterceptor(new LoginHandlerInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns(
                "/index.html", "/", "/login", "/css/**", "/js/**","/images/**", "", "/static/**",
                "/register.html", "/register", "/user/checkCode", "/toAdmin", "/admin", "/lib/**"
                ,"/swagger-ui.html","/swagger-ui/**","/swagger-resources/**","/v3/api-docs/**","/webjars/**","/v3/api-docs.yml"
                ,"/druid/**",
                        "/actuator/**");
    }

}
