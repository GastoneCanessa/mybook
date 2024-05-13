package com.book.mybook;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Disables CSRF protection for simplicity
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/register", "/login").permitAll() // Allows access to registration and login
                        .anyRequest().authenticated()) // Requires authentication for all other requests
                .formLogin(formLogin -> formLogin
                        .loginPage("/login") // Specifies the custom login page
                        .defaultSuccessUrl("/home", true) // Redirects to the home page after successful login
                        .permitAll()) // Allows everyone to access the login page
                .logout(logout -> logout.permitAll()); // Allows everyone to access the logout functionality

        return http.build();
    }

}
