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
            //RUBROS ROUTES
            new AntPathRequestMatcher("/rubros/get-all"),
            //MICROEMPRENDIMIENTOS ROUTES
            new AntPathRequestMatcher("/microemprendimientos/findByName"),
            new AntPathRequestMatcher("/microemprendimientos/findByRubro/{id}"),
            new AntPathRequestMatcher("/microemprendimientos/findById/{idMicroemprendimiento}")
    );

    RequestMatcher adminUrls = new OrRequestMatcher(
            //USER ROUTES
            new AntPathRequestMatcher("/usuarios/crear"),
            new AntPathRequestMatcher("/usuarios/editar/{id}"),
            new AntPathRequestMatcher("/usuarios/baja/{id}"),
            //PROVINCIA ROUTES
            new AntPathRequestMatcher("/provincias/{id}"),
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
            //MICROEMPRENDIMIENTOS ROUTES
            new AntPathRequestMatcher("/microemprendimientos/admin/create"),
            new AntPathRequestMatcher("/microemprendimientos/admin/edit/{idMicroemprendimiento}"),
            new AntPathRequestMatcher("/microemprendimientos/admin/hide/{idMicroemprendimiento}"),
            new AntPathRequestMatcher("/microemprendimientos/admin/findByUser"),
            new AntPathRequestMatcher("/microemprendimientos/admin/estadisticasGenerales"),
            //RUBROS ROUTES
            new AntPathRequestMatcher("/rubros/admin/estadisticasPorUsuario")

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
