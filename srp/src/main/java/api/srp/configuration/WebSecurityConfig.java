package api.srp.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebMvc
public class WebSecurityConfig implements WebMvcConfigurer {
	
    //LIBERA O CORS
    public void addCorsMappings(CorsRegistry registry) {//addCorsMappings() - é um metodo do WebMvcConfigurer que implementamos nessa classe nós acessamos ele e utilizamos para mexer em coisas em relção ao cors...
    	
    	registry.addMapping("/**") //libera o uso de todas as rotas (depois de barra todo mundo "/**")
    	.allowedOrigins("*"); //libera acesso de todas as origens
    	
    }
	
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
