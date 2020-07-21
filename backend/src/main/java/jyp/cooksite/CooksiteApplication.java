package jyp.cooksite;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;

@SpringBootApplication
public class CooksiteApplication {

	public static void main(String[] args) {
		
		
		SpringApplication.run(CooksiteApplication.class, args);
		
		
		
	}
	@Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
	@Bean
	Hibernate5Module hibernate5Module() {
		return new Hibernate5Module();
	}
}
