package com.mandiri.Security;

import com.mandiri.entity.Role;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {


    private final AuthTokenFilter auth;

    private final String[] WHITE_LIST_URL = {
            "/api/auth/**",
            "/swagger-ui/**",
            "/swagger-ui.html/**",
            "/v3/api-docs/**"
    };


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.httpBasic(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable)
                .cors(AbstractHttpConfigurer::disable)
                .sessionManagement(httpSecuritySessionManagementConfigurer -> httpSecuritySessionManagementConfigurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(req ->
                        req.requestMatchers(WHITE_LIST_URL).permitAll()
                                .requestMatchers("/api/v1/customer/admin/**").hasAnyAuthority("ROLE_ADMIN","ROLE_STAFF")
                                .requestMatchers("/api/v1/customer/update ").hasAnyAuthority("ROLE_ADMIN","ROLE_STAFF","ROLE_CUSTOMER")
                                .requestMatchers("/api/v1/customer/avatar/**").hasAnyAuthority("ROLE_ADMIN","ROLE_STAFF","ROLE_CUSTOMER")
                                .requestMatchers("/api/v1/loan/admin/**").hasAnyAuthority("ROLE_ADMIN","ROLE_STAFF")
                                .requestMatchers("/api/v1/loan/**").hasAnyAuthority("ROLE_ADMIN","ROLE_STAFF","ROLE_CUSTOMER")
                                .requestMatchers("/api/v1/loan/pay").hasAnyAuthority("ROLE_CUSTOMER").anyRequest().authenticated()
                ).addFilterBefore(auth, UsernamePasswordAuthenticationFilter.class);
        return http.build();

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

}
