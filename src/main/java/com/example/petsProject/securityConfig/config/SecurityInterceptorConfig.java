package com.example.petsProject.securityConfig.config;

import com.example.petsProject.memberImpl.MemberMapper;
import com.example.petsProject.securityConfig.interceptor.SocialLoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebSecurity
public class SecurityInterceptorConfig implements WebMvcConfigurer {
    private String connectPath_profile = "/profile_img/**";
    private String resourcePath_profile = "file:///Users/yeajikim/Desktop/upload_img/profile/";
    private String connectPath_post = "/post_img/**";
    private String resourcePath_post = "file:///Users/yeajikim/Desktop/upload_img/post/";
    @Autowired
    private MemberMapper mapper;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new SocialLoginInterceptor(mapper)).addPathPatterns("/user/oauthSuccess");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(connectPath_profile)
                .addResourceLocations(resourcePath_profile);
        registry.addResourceHandler(connectPath_post)
                .addResourceLocations(resourcePath_post);
    }
}
