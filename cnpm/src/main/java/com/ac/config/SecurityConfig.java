package com.ac.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.firewall.HttpFirewall;
import org.springframework.security.web.firewall.StrictHttpFirewall;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())  // Tắt CSRF (tránh lỗi CSRF khi test API)
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().permitAll() // Cho phép tất cả API truy cập mà không cần xác thực
                )
                .formLogin(form -> form.disable())  // Tắt form login mặc định của Spring Security
                .httpBasic(httpBasic -> httpBasic.disable()); // Tắt xác thực HTTP Basic

        return http.build();
    }

    @Bean
    public HttpFirewall allowUrlEncodedNewline() {
        StrictHttpFirewall firewall = new StrictHttpFirewall();
        firewall.setAllowUrlEncodedPeriod(true); // Cho phép %0A trong URL
        return firewall;
    }
}
