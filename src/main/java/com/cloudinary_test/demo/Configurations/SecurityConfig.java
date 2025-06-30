package com.cloudinary_test.demo.Configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http
                .csrf(csrf -> csrf.disable()) //Desactivo CSRF em desarrollo
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                           "/swagger-ui/**",
                           "/v3/api/docs",
                           "/images/**",
                           "/comments/**",
                           "/categories/**",
                           "users/**"
                        ).permitAll()
                        .anyRequest().permitAll()

                );
        return http.build();
    }

}
