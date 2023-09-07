package com.welcome.blog.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    @Bean
    BCryptPasswordEncoder encodePWD() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable() // csrf토큰 테스트시 비활성화
                .authorizeRequests()
                .antMatchers("/auth/**", "/js/**", "/css/**", "/image/**")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .loginPage("/auth/loginForm")
                .loginProcessingUrl("/auth/loginProc")
                .defaultSuccessUrl("/");
        return http.build();
    }
}
