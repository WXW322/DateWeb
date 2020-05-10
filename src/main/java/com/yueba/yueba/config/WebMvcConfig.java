package com.yueba.yueba.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author 徐塬峰
 * @email 986771570@qq.com
 * @date 2019-05-08
 * @description
 **/
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    public String mvcFileUpload;

    @Value("${mvcFileUpload}")
    public void setMvcFileUpload(String fileUpload) {
        mvcFileUpload = fileUpload;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");

        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
        //映射本地服务器上的图片到url上
        registry.addResourceHandler("/image/**").
                addResourceLocations("file:/Users/xuyuanfeng/image/");
    }
}
