package kz.alken1t.moviedatabaseprojectruntime.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class ConfigSecurity {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http.csrf().disable().authorizeHttpRequests(auth ->{
            auth.requestMatchers("/detail/delete","/login").authenticated()
                    .anyRequest().permitAll();
        });
        return http.build();
    }
}
