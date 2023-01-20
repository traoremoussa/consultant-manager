package com.kodiatech.traore.config;


import com.kodiatech.traore.auth.dao.UserDao;
import com.kodiatech.traore.config.jwt.JwtAuthFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.function.Function;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor //
public class SecurityConfig {
    private final JwtAuthFilter jwtAuthFilter;


    /**
     * cette methode est alimentée dans ApplicationConfig.java pricipe(@Bean)
     * https://www.baeldung.com/spring-bean
     * probleme de reponse http code status
     * https://www.baeldung.com/spring-cors
     */
    private final AuthenticationProvider authenticationProvider;


    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.cors().and()
                .csrf().disable()
                .authorizeHttpRequests()
                .requestMatchers("/*/*/auth/**").permitAll()
                .requestMatchers("/api/v1/consultant/addCons").permitAll()
                //
                .requestMatchers("/**").permitAll()
                //
                .anyRequest()
                .authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)//session etat n'est sauver
                .and()
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
        ;
        return http.build();
    }


}
