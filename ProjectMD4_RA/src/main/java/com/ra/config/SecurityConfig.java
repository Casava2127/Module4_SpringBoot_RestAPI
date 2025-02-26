package com.ra.config;

import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // Vô hiệu hóa CSRF nếu không cần thiết cho API REST (hoặc cấu hình lại nếu cần)
                .csrf(csrf -> csrf.disable())
                // Cấu hình phân quyền truy cập
                .authorizeHttpRequests(authorize -> authorize
                        // Cho phép truy cập công khai đến các endpoint cụ thể (nếu cần)
                        .requestMatchers("/api/public/**").permitAll()
                        // Các request khác đều cần xác thực
                        .anyRequest().authenticated()
                )
                // Sử dụng HTTP Basic (theo cách lambda DSL)
                .httpBasic(withDefaults());

        return http.build();
    }
}
