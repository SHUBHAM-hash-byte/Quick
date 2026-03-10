package com.quick.web.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;




@Configuration
@EnableWebSecurity
public class SecurityConfig {


    @Bean
    SecurityFilterChain deSecurityFilterChain(HttpSecurity httpSecurity)throws  Exception{

        httpSecurity
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests((authorize) -> authorize.requestMatchers("/auth/**", "/auth/hello","/auth/login","/h2-console/**")
                        .permitAll()
                        .anyRequest()
                        .authenticated()
                )
        .headers(headers -> headers.frameOptions(frame -> frame.sameOrigin()))

        .sessionManagement(session ->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));


        return httpSecurity.build();
    }



    @Bean
    public PasswordEncoder passwordEncoder() {

        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception{
        return configuration.getAuthenticationManager();
    }
}
