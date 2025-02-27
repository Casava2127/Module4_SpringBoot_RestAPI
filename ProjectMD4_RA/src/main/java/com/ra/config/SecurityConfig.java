package com.ra.config;//package com.ra.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.authentication.Http403ForbiddenEntryPoint;
//
//@Configuration
//public class SecurityConfig {
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .csrf(csrf -> csrf.disable())  // Tắt CSRF nếu API không cần
//                .authorizeHttpRequests(auth -> auth
//                        .requestMatchers("/api/v1/auth/**").permitAll()  // Cho phép đăng ký & đăng nhập mà không cần token
//                        .requestMatchers("/api/v1/users/**").permitAll()  // ko Cần xác thực để truy cập user
//                        .anyRequest().authenticated()
//                )
//                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))  // Nếu dùng JWT, API sẽ stateless
//                .exceptionHandling(ex -> ex.authenticationEntryPoint(new Http403ForbiddenEntryPoint())); // Xử lý lỗi 403
//
//        return http.build();
//    }
//}
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
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
}
