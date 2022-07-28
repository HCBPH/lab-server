package com.ycj.lab.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author 53059
 * @date 2021/6/2 20:41
 */
@Configuration
class WebMvcConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/")
                .addResourceLocations("file:/d:/cache/tmp/lab/")
                .addResourceLocations("file:/var/lab/");
    }
}
