package com.taocaicai.jwt.config;

import com.taocaicai.jwt.interceptor.TokenInterceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

/**
 * @project spring-redis
 * @author Oakley
 * @created 2021-08-11 22:12:22:12
 * @package org.taocaicai.config
 * @description TODO
 * @version: 0.0.0.1
 */

@Configuration
public class IntercepterConfig implements WebMvcConfigurer {
    private static Logger logger = LoggerFactory.getLogger(IntercepterConfig.class);
    @Autowired
    private TokenInterceptor tokenInterceptor;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        logger.info("----------- init web config------------");
        List<String> excludePath = new ArrayList<>();
        /**注册*/
        excludePath.add("/user_register");
        /**登录*/
        excludePath.add("/login");
        /**登出*/
        excludePath.add("/logout");
        /**静态资源*/
        excludePath.add("/static/**");
        /**静态资源*/
        excludePath.add("/assets/**");

        registry.addInterceptor(tokenInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns(excludePath);
        WebMvcConfigurer.super.addInterceptors(registry);
    }
}
