package com.example.rx.config;

import com.example.rx.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private UserService userService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((requests) -> requests
                        // Publicly accessible endpoints
                        .requestMatchers(
                                "/login",
                                "/resources/**",
                                "/h2-console/**",
                                "/api/v1/prescription/**",
                                "/api/v1/rxnav/**",   // <-- Add this to allow RxNav API
                                "/swagger-ui/**",
                                "/v3/api-docs/**"
                        ).permitAll()
                        // Any other request requires authentication
                        .anyRequest().authenticated()
                )
                .formLogin((form) -> form
                        .loginPage("/login")
                        .defaultSuccessUrl("/prescriptions")
                        .permitAll()
                )
                .logout((logout) -> logout.permitAll())
                .csrf(csrf -> csrf.disable()) // Disable CSRF for simplicity in API calls
                .headers(headers -> headers.frameOptions(frameOptions -> frameOptions.disable())); // For H2 console

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
