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

		registry.addMapping("/**") // 모든 요청에 대해서
		.allowedMethods("HEAD", "GET", "PUT", "POST", "DELETE", "PATCH")
				.allowedOrigins("http://localhost:8080") // 허용할 오리진들
				.allowCredentials(true);
	}
	//web root가 아닌 외부 경로에 있는 리소스를 url로 불러올 수 있도록 설정
    //현재 localhost:8090/summernoteImage/1234.jpg
    //로 접속하면 C:/summernote_image/1234.jpg 파일을 불러온다.
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
    	registry.addResourceHandler("/resources/**").addResourceLocations("/resources/"); 
    	
        registry.addResourceHandler("/image/**")
                .addResourceLocations("file:///C:/spring/Myproject/workspace2/Cookword/front/src/image/")
                ;
    }
    
    @Bean // 세션 언어설정. default는 KOREAN = 'ko'
    public LocaleResolver localeResolver() {
        SessionLocaleResolver slr = new SessionLocaleResolver();
        slr.setDefaultLocale(Locale.KOREAN);
        return slr;
    }

    @Bean //lang 파라미터에 따라 언어가 변경되도록 설정 
    public LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
        lci.setParamName("lang");
        return lci;
    }

    @Override // 인터셉터 등록 
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localeChangeInterceptor());
    }
    
    @Bean
    public MessageSource messageSource() {

    	ResourceBundleMessageSource source = new ResourceBundleMessageSource();

	// 메세지 프로퍼티파일의 위치와 이름을 지정한다.
        source.setBasename("i18n/exception");
        // 기본 인코딩을 지정한다.
        source.setDefaultEncoding("UTF-8");
        // 프로퍼티 파일의 변경을 감지할 시간 간격을 지정한다.
       // source.setCacheSeconds(60);
        // 없는 메세지일 경우 예외를 발생시키는 대신 코드를 기본 메세지로 한다.
        source.setUseCodeAsDefaultMessage(true);
        return source;
    }

//    @Bean // yml 파일을 참조하는 MessageSource 선언
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

   //  locale 정보에 따라 다른 yml 파일을 읽도록 처리
//    private static class YamlMessageSource extends ResourceBundleMessageSource {
//        @Override
//        protected ResourceBundle doGetBundle(String basename, Locale locale) throws MissingResourceException {
//        	return ResourceBundle.getBundle(basename, locale, YamlResourceBundle.Control.INSTANCE);
//            //return ResourceBundle.getBundle(basename, locale, YamlResourceBundle.Control.INSTANCE);
//        }
//    }

}
