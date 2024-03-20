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
/*
* Se puede configurar de otra forma, prohibiendo solo algunos endpoints y permitiendo todos los demás
* */
    RequestMatcher publicUrls = new OrRequestMatcher(
            new AntPathRequestMatcher("/auth/token"),
            new AntPathRequestMatcher("/publicaciones/permitidas"),
            new AntPathRequestMatcher("/publicaciones/{id}"),
            new AntPathRequestMatcher("/publicaciones/ultimasTres"),
            new AntPathRequestMatcher("/cloudinary/upload"),
            new AntPathRequestMatcher("/mensaje/create")
    );

    RequestMatcher adminUrls = new OrRequestMatcher(
            //USER ROUTES
            new AntPathRequestMatcher("/usuarios/crear"),
            new AntPathRequestMatcher("/usuarios/editar/{id}"),
            new AntPathRequestMatcher("/usuarios/baja/{id}"),
            //PROVINCIA ROUTES
            new AntPathRequestMatcher("/provincias/{id}"),
            //RUBROS ROUTES
            new AntPathRequestMatcher("/rubros/get-all"),
            //USER AUTH ROUTES
            new AntPathRequestMatcher("/auth/user/details"),
            //PAIS ROUTES
            new AntPathRequestMatcher("/paises/get-all"),
            //PUBLICACIONES ROUTES
            new AntPathRequestMatcher("/publicaciones/admin/create"),
            new AntPathRequestMatcher("/publicaciones/admin/getAll"),
            new AntPathRequestMatcher("/publicaciones/admin/edit/{id}"),
            new AntPathRequestMatcher("/publicaciones/admin/baja/{id}"),
            new AntPathRequestMatcher("/publicaciones/add-image"),
            //IMAGE ROUTES
            new AntPathRequestMatcher("/image/delete/{id}"),
            new AntPathRequestMatcher("/image/create"),
            //MENSAJE ROUTES
            new AntPathRequestMatcher("/mensaje"),
            new AntPathRequestMatcher("/mensaje/gestionado"),
            new AntPathRequestMatcher("/mensaje/{id}")

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
