package jyp.cooksite.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfigurerImpl implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
    	
        registry.addMapping("/**") //��� ��û�� ���ؼ�
                .allowedOrigins("http://localhost:8080"); //����� ��������
    }
}