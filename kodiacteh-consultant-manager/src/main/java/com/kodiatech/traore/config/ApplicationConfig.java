package com.kodiatech.traore.config;


import com.kodiatech.traore.profiles.repositories.UtilisateurRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;


import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@RequiredArgsConstructor
public class ApplicationConfig {

  private final UtilisateurRepository utilisateurRepository;

   // private final UserDetailsServiceImpl userDetailsService;
    /*
        @Bean
        public UserDetailsService userDetailsService() {
            return new UserDetailsService() {
                @Override
                public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
                    return utilisateurRepository.findByEmail(email)
                            .orElseThrow(() -> new UsernameNotFoundException("User not found"));
                }
            };
        }
    */

    /**
     * j'avais fais une class (UserDetailsServiceImpl) mais pas encore utiliser
     * @return UserDetailsService
     */

    @Bean
    public UserDetailsService userDetailsService() {
        return email -> utilisateurRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }




    //utiliser par authentication manager (dans service ) je sais pas
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
/**
//=========================== Pour identifier swagger
*/
/*
    public AuthenticationManager authManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder =
                http.getSharedObject(AuthenticationManagerBuilder.class);
        //par badd
       authenticationManagerBuilder.authenticationProvider(authenticationProvider());
       //par memoire
        authenticationManagerBuilder.authenticationProvider(inMemoryDaoAuthenticationProvider());
        return authenticationManagerBuilder.build();
    }

    @Bean
    public DaoAuthenticationProvider inMemoryDaoAuthenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(inMemoryUserDetailsService());
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return daoAuthenticationProvider;
    }
    public UserDetailsService inMemoryUserDetailsService() {
        UserDetails user1 = User.builder()
                .username("user")
                .password("user")
                .roles("ADMIN")
                .passwordEncoder((password) -> passwordEncoder().encode(password))
                .build();
        return new InMemoryUserDetailsManager(user1);
    }

*/





}

//TODO good for spring security
//https://www.bezkoder.com/spring-boot-jwt-auth-mongodb/

//TODO MULTI Provider
//https://www.baeldung.com/spring-security-multiple-auth-providers
//https://github.com/initgrep-code-examples/nauth/blob/auth-providers/src/main/java/com/initgrep/apps/nauth/config/SecurityConfig.java