package jyp.cooksite.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.google.common.collect.ImmutableList;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    private final JwtTokenProvider jwtTokenProvider;
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .httpBasic().disable().cors().and() // rest api �̹Ƿ� �⺻���� ������. �⺻������ �������� �α����� ȭ������ �����̷�Ʈ �ȴ�.
            .csrf().disable() // rest api�̹Ƿ� csrf ������ �ʿ�����Ƿ� disableó��.
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) // jwt token���� �����ϹǷ� ������ �ʿ�����Ƿ� ��������.
            .and()
            
                .authorizeRequests() // ���� ������Ʈ�� ���� ������ üũ
                    .antMatchers("/*/login", "/*/join","/**/image/**").permitAll() // ���� �� ���� �ּҴ� ������ ���ٰ���
                    //.antMatchers(HttpMethod.GET, "helloworld/**").permitAll() // hellowworld�� �����ϴ� GET��û ���ҽ��� ������ ���ٰ���
                    .anyRequest().hasRole("USER") // �׿� ������ ��û�� ��� ������ ȸ���� ���� ����
            .and()
   .addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class); // jwt token ���͸� id/password ���� ���� ���� �ִ´�
    
       
    }
    @Override // ignore check swagger resource
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers("/v2/api-docs", "/swagger-resources/**",
                "/swagger-ui.html", "/webjars/**", "/swagger/**");
        
    }
   
    
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
    	CorsConfiguration configuration = new CorsConfiguration();

        configuration.addAllowedOrigin("http://localhost:8080");
        configuration.addAllowedHeader("*");
        configuration.addAllowedMethod("*");
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
