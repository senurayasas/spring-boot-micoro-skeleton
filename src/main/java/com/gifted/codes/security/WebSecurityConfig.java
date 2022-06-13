package com.gifted.codes.security;

import com.nimbusds.jose.shaded.json.JSONArray;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

@Slf4j
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
@ConditionalOnProperty(prefix = "gifted.security", name = "enabled", havingValue = "true", matchIfMissing = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/actuator/health/**").permitAll()
                .antMatchers("/actuator/prometheus").permitAll()
                .antMatchers("/swagger-ui/**", "/v3/**", "/swagger-ui.html").permitAll()
                .anyRequest().authenticated()
                .and()
                .oauth2ResourceServer().jwt()
                .jwtAuthenticationConverter(new JwtAuthenticationConverter() {
                    @Override
                    protected Collection<GrantedAuthority> extractAuthorities(final Jwt jwt) {
                        JSONArray authorities = jwt.getClaim("authorities");
                        return authorities.stream()
                                .map(role -> new SimpleGrantedAuthority(role.toString()))
                                .collect(Collectors.toCollection(ArrayList::new));
                    }
                });
    }

}