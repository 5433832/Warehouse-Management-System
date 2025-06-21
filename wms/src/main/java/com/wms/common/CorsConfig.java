package com.wms.common;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration  //标识配置类
public class CorsConfig implements WebMvcConfigurer {
 @Override
 public void addCorsMappings(CorsRegistry registry) {
 registry.addMapping("/**")
 //是否发送Cookie
 .allowCredentials(true)
 //放⾏哪些原始域
 .allowedOriginPatterns("*")
         //允许的请求方法
 .allowedMethods(new String[]{"GET", "POST", "PUT", "DELETE"})
         // 允许的请求头
 .allowedHeaders("*")
         // 允许暴露的响应头
 .exposedHeaders("*");
 }
}