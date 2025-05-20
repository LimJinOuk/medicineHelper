package com.jinouk.medicinehelper.global.jwt.jwtUtil;

import com.jinouk.medicinehelper.global.exception.customExceptionHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig
{
    private final jwtUtil jwtUtil;
    private final customExceptionHandler customExceptionHandler;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/" ,
                                "/login",
                                "/register",
                                "/DoLogin",
                                "doRegister",
                                "/CSS/**",
                                "/js/**",
                                "/favicon.ico"
                        ).permitAll()
                        .anyRequest().authenticated()
                )
                .exceptionHandling(ex -> ex
                        .authenticationEntryPoint(customExceptionHandler) // 401
                        .accessDeniedHandler(customExceptionHandler)      // 403
                )
                .addFilterBefore(new JWTAuthFilter(jwtUtil), UsernamePasswordAuthenticationFilter.class)
                .build();
    }

}
