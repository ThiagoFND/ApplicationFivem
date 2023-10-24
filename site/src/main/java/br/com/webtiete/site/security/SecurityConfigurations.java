package br.com.webtiete.site.security;

import org.springframework.beans.factory.annotation.Autowired;
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
    private SecurityFilter securityFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .cors() // Ativar as configurações de CORS
                .and()
                .csrf().disable()
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeRequests(authorize -> authorize
                        .requestMatchers(HttpMethod.POST, "/api/auth/login").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/auth/register").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/dados/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/dados/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/api/dados/create").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/dados/list").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/user/**").hasRole("ADMIN")
                        .requestMatchers("/api/prisao/**").hasRole("POLICIAL")
                        .requestMatchers(HttpMethod.GET, "/api/edital/list**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/edital/create").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/edital/update").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/edital/delete").hasRole("ADMIN")
                        .anyRequest().authenticated()
                )
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
