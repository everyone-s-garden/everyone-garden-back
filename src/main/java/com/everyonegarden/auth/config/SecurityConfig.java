package com.everyonegarden.auth.config;

import com.everyonegarden.auth.jwt.AuthTokenProvider;
import com.everyonegarden.auth.jwt.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfigurationSource;

@EnableWebSecurity
@RequiredArgsConstructor
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final AuthTokenProvider authTokenProvider;
    private final CorsConfigurationSource corsConfigurationSource;

    @Override
    public void configure(WebSecurity web) {
        web.ignoring().
                antMatchers(
                        "/v2/api-docs",
                        "/configuration/**",
                        "/swagger*/**",
                        "/webjars/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        JwtAuthenticationFilter jwtTokenValidationFilter = new JwtAuthenticationFilter(authTokenProvider);

        http
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, Constants.permitAllArray).permitAll()
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

                .addFilterBefore(jwtTokenValidationFilter, UsernamePasswordAuthenticationFilter.class);
    }

}
