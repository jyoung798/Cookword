package jyp.cooksite.config;



import java.io.IOException;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;


import net.rakugakibox.util.YamlResourceBundle;

@Configuration
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
    	registry.addResourceHandler("/resources/**").addResourceLocations("/resources/"); 
    	
        registry.addResourceHandler("/image/**")
                .addResourceLocations("file:///C:/spring/Myproject/workspace2/Cookword/front/src/image/")
                ;
    }
    
    @Bean // ���� ����. default�� KOREAN = 'ko'
    public LocaleResolver localeResolver() {
        SessionLocaleResolver slr = new SessionLocaleResolver();
        slr.setDefaultLocale(Locale.KOREAN);
        return slr;
    }

    @Bean //lang �Ķ���Ϳ� ���� �� ����ǵ��� ���� 
    public LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
        lci.setParamName("lang");
        return lci;
    }

    @Override // ���ͼ��� ��� 
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localeChangeInterceptor());
    }
    
    @Bean
    public MessageSource messageSource() {

    	ResourceBundleMessageSource source = new ResourceBundleMessageSource();

	// �޼��� ������Ƽ������ ��ġ�� �̸��� �����Ѵ�.
        source.setBasename("i18n/exception");
        // �⺻ ���ڵ��� �����Ѵ�.
        source.setDefaultEncoding("UTF-8");
        // ������Ƽ ������ ������ ������ �ð� ������ �����Ѵ�.
       // source.setCacheSeconds(60);
        // ���� �޼����� ��� ���ܸ� �߻���Ű�� ��� �ڵ带 �⺻ �޼����� �Ѵ�.
        source.setUseCodeAsDefaultMessage(true);
        return source;
    }

//    @Bean // yml ������ �����ϴ� MessageSource ����
//    public MessageSource messageSource(
//            @Value("${spring.messages.basename}") String basename, //i18n/exception
//            @Value("${spring.messages.encoding}") String encoding //UTF-8
//    ) {
//    	
//    	
//        YamlMessageSource ms = new YamlMessageSource();
//        
//        
//        ms.setBasename("i18n/exception");
//        ms.setDefaultEncoding("UTF-8");
//        ms.setAlwaysUseMessageFormat(true);
//        ms.setUseCodeAsDefaultMessage(true);
//        ms.setFallbackToSystemLocale(true);
//        return ms;
//    }

   //  locale ������ ���� �ٸ� yml ������ �е��� ó��
//    private static class YamlMessageSource extends ResourceBundleMessageSource {
//        @Override
//        protected ResourceBundle doGetBundle(String basename, Locale locale) throws MissingResourceException {
//        	return ResourceBundle.getBundle(basename, locale, YamlResourceBundle.Control.INSTANCE);
//            //return ResourceBundle.getBundle(basename, locale, YamlResourceBundle.Control.INSTANCE);
//        }
//    }

}
