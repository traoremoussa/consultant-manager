package com.kodiatech.traore.config;


import com.kodiatech.traore.config.jwt.JwtAuthFilter;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor //
@OpenAPIDefinition(info = @Info(title = "Consultant API", version = "2.0", description = "Consultant Information"))
@SecurityScheme(name = "kodiatech-api", scheme = "basic", type = SecuritySchemeType.HTTP, in = SecuritySchemeIn.HEADER)
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
                //enlever
               // .requestMatchers("/**").permitAll()
                //
                .requestMatchers("/swagger-ui/**","/v3/api-docs/**").permitAll()
                //.requestMatchers("/v3/api-docs/**", "/swagger-ui/**").permitAll()

                //
                .anyRequest()
                .authenticated()
                .and().httpBasic()
             .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)//session etat n'est sauver
                .and()
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
                /*
                TODO connection by Google api token
                .oauth2Login(oAuth2LoginConfigurer->{
                    oAuth2LoginConfigurer.loginProcessingUrl("/goole")
                            .loginPage("/ao.....").successHandler((request,response,authentication)->{

                            });

                });
        ;*/
        return http.build();
    }

//https://www.youtube.com/watch?v=GkFTNz60ynU
}
