package com.situ.stmail.manager.config;

import com.situ.stmail.manager.intercepter.AdminIntercepter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Value("${upload.path}")
    private String path;
    @Autowired
    private AdminIntercepter adminIntercepter;
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/upload/**")
                .addResourceLocations("file:"+path);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(adminIntercepter).addPathPatterns("/**").excludePathPatterns("/layui/**","/admin/login.html","/vercode","/api/admin/login");
    }
}
