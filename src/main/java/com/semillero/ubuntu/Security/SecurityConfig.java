package com.semillero.ubuntu.Security;

import com.semillero.ubuntu.Security.jwt.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthFilter;

    RequestMatcher publicUrls = new OrRequestMatcher(
            new AntPathRequestMatcher("/auth/token"),
            new AntPathRequestMatcher("/publicaciones/permitidas")
    );

    RequestMatcher adminUrls = new OrRequestMatcher(
            new AntPathRequestMatcher("/rubros/get-all"),
            new AntPathRequestMatcher("/auth/user/details"),
            new AntPathRequestMatcher("/paises/**"),
            new AntPathRequestMatcher("/provincias/**"),
            new AntPathRequestMatcher("admin/publicaciones/getAll"),
            new AntPathRequestMatcher("admin/publicaciones/create"),
            new AntPathRequestMatcher("admin/publicaciones/edit/{id}"),
            new AntPathRequestMatcher("admin/publicaciones/baja/{id}")
    );

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests((authorize) -> authorize
                        .requestMatchers(publicUrls)
                        .permitAll()
                        .requestMatchers(adminUrls).hasRole("ADMINISTRADOR")
                        .anyRequest()
                        .authenticated()
                )
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
