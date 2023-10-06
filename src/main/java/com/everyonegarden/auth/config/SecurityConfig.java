package com.everyonegarden.auth.config;

import com.everyonegarden.auth.jwt.AuthTokenProvider;
import com.everyonegarden.auth.jwt.JwtAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.util.stream.Stream;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final AuthTokenProvider authTokenProvider;

    public SecurityConfig(AuthTokenProvider authTokenProvider) {
        this.authTokenProvider = authTokenProvider;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        JwtAuthenticationFilter jwtTokenValidationFilter = new JwtAuthenticationFilter(authTokenProvider);

        http
                .authorizeHttpRequests(request -> request
                        .requestMatchers(
                                Stream
                                        .of(Constants.permitAllArray)
                                        .map(AntPathRequestMatcher::antMatcher)
                                        .toArray(AntPathRequestMatcher[]::new)
                        )
                        .permitAll()
                        .anyRequest().authenticated())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .csrf(AbstractHttpConfigurer::disable)
                .httpBasic().disable()
                .formLogin().disable()
                .addFilterBefore(jwtTokenValidationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

}
