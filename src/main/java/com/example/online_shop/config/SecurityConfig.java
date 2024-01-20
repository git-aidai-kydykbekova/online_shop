package com.example.online_shop.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration     //Обозначает, что этот класс предоставляет конфигурацию для Spring.
@EnableGlobalMethodSecurity(prePostEnabled = true) //Включает глобальную метод-безопасность с использованием аннотаций, таких как @PreAuthorize и @Secured.
@EnableWebSecurity    //Включает конфигурацию безопасности для веб-приложения.
@ComponentScan("com.example.online_shop.config")

public class SecurityConfig {
    @Bean
    SecurityFilterChain securityFilterChain(final HttpSecurity http) throws Exception {
        http.csrf(CsrfConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/auth/**", "/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html", "/phone/**").permitAll()
                        .requestMatchers("/phone/add/**","/country/add/**", "/phone/list", "/country/countries","/phone/my/basket").permitAll()

                )              ;
        return http.build();
    }
}

