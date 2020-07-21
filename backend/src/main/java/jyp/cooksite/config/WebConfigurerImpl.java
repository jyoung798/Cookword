package jyp.cooksite.config;



import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;

@Configuration
@Component
public class WebConfigurerImpl implements WebMvcConfigurer {
	@Override
	public void addCorsMappings(CorsRegistry registry) {

		registry.addMapping("/**") // ��� ��û�� ���ؼ�
		.allowedMethods("HEAD", "GET", "PUT", "POST", "DELETE", "PATCH")
				.allowedOrigins("http://localhost:8080") // ����� ��������
				.allowCredentials(true);
	}
	//web root�� �ƴ� �ܺ� ��ο� �ִ� ���ҽ��� url�� �ҷ��� �� �ֵ��� ����
    //���� localhost:8090/summernoteImage/1234.jpg
    //�� �����ϸ� C:/summernote_image/1234.jpg ������ �ҷ��´�.
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/image/**")
                .addResourceLocations("file:///C:/spring/Myproject/workspace2/Cookword/front/src/image/");
    }
	

}
