package api.srp.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class WebSecurityConfig {
	
	/* OQUE É UM @Bean ?
	 * Um Bean no Spring é basicamente um componente da sua aplicação que 
	 * o Spring gerencia e pode injetar automaticamente onde ele for necessário. 
	 * */
	
    // Define o PasswordEncoder como um Bean
    @Bean 
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Configura a segurança usando HttpSecurity
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    	
    	/*
    	 * O SecurityFilterChain define uma série de filtros de segurança que serão aplicados a todas as requisições HTTP que a sua aplicação recebe.
    	 * */
    	
        http.csrf().disable().authorizeRequests().anyRequest().permitAll();

        return http.build();
    }
}
