package com.catalisa.apiDoacaoAlimento.config.security;

import com.catalisa.apiDoacaoAlimento.model.Usuarios;
import com.catalisa.apiDoacaoAlimento.model.dto.RegistroDto;
import com.catalisa.apiDoacaoAlimento.model.enuns.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;

import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfigurations {
    @Autowired
    SecurityFilter securityFilter;
    @Autowired
    private TokenService tokenService;


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http.csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
                        .antMatchers("/swagger-ui.html", "/swagger-ui/**", "/v3/api-docs/**").permitAll()
                        .antMatchers(HttpMethod.POST,"/auth/login").permitAll()
                        .antMatchers(HttpMethod.POST,"/auth/registro").permitAll()
                        .antMatchers(HttpMethod.POST, "/api/doacoes").hasAnyRole("USER")
                        .antMatchers(HttpMethod.DELETE, "/api/doacoes/{id}").hasRole("ADMIN")
                        .antMatchers(HttpMethod.GET, "/api/doacoes/buscarPorAlimento").hasRole("ADMIN")
                        .antMatchers(HttpMethod.GET, "/api/doacoes/buscarPorCidade").hasRole("ADMIN")
                        .antMatchers(HttpMethod.GET, "/api/doacoes/buscarPorNome").hasRole("ADMIN")
                        .antMatchers(HttpMethod.GET, "/api/doacoes").hasRole("ADMIN")
                        .antMatchers(HttpMethod.GET, "/api/doacoes/{id}").hasRole("ADMIN")
                        .antMatchers(HttpMethod.PUT, "/api/doacoes/{id}").hasRole("USER")
                        .anyRequest().authenticated())
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
