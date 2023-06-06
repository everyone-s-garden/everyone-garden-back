package com.everyonegarden.auth.config;


import com.everyonegarden.auth.jwt.AuthTokenProvider;
import com.everyonegarden.auth.jwt.JwtAuthenticationFilter;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;


@RequiredArgsConstructor
@Configuration
@EnableWebSecurity

public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final AuthTokenProvider authTokenProvider;
    private final CorsConfigurationSource corsConfigurationSource;

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().
                antMatchers(
                        "/v2/api-docs",
                        "/configuration/**",
                        "/swagger*/**",
                        "/webjars/**")
                .antMatchers(HttpMethod.GET,"/v1/garden/{gardenID:[\\d+]}");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        JwtAuthenticationFilter jwtAuthFilter = new JwtAuthenticationFilter(authTokenProvider);

        http
                .authorizeRequests()
//                .antMatchers(HttpMethod.GET,"/v1/garden/{gardenID:[\\d+]}").permitAll()
                .antMatchers(Constants.permitAllArray).permitAll()
                .antMatchers(HttpMethod.OPTIONS).permitAll()
                .anyRequest().authenticated()

                .and()

                .headers()
                .frameOptions()
                .sameOrigin()

                .and()

                .cors()
                .configurationSource(corsConfigurationSource)
                .and()

                .csrf().disable()

                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

                .and()

                .oauth2Login()

                .and()

                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
    }

}
