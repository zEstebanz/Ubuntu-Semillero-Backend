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
            new AntPathRequestMatcher("/email"),
            //MICROEMPRENDIMIENTOS ROUTES
            new AntPathRequestMatcher("/microemprendimientos/findAll"),
            new AntPathRequestMatcher("/microemprendimientos/findByRubro/{idRubro}"),
            //MENSAJE ROUTES
            new AntPathRequestMatcher("/mensaje/create"),
            //GESTIONINVERSION ROUTES
            new AntPathRequestMatcher("/gestionInversion/calcularInversion"),
            new AntPathRequestMatcher("/gestionInversion/{idMicro}"),
            //CHATBOT ROUTES
            new AntPathRequestMatcher("/faq/initials"),
            new AntPathRequestMatcher("/faq/answer/{id}")
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
            new AntPathRequestMatcher("/publicaciones/admin/ultimasDiez"),
            //IMAGE ROUTES
            new AntPathRequestMatcher("/image/delete/{id}"),
            new AntPathRequestMatcher("/image/create"),
            //MICROEMPRENDIMIENTOS ROUTES
            new AntPathRequestMatcher("/microemprendimientos/admin/create"),
            new AntPathRequestMatcher("/microemprendimientos/admin/edit/{idMicroemprendimiento}"),
            new AntPathRequestMatcher("/microemprendimientos/admin/hide/{idMicroemprendimiento}"),
            new AntPathRequestMatcher("/microemprendimientos/admin/findById/{idMicroemprendimiento}"),
            new AntPathRequestMatcher("/microemprendimientos/admin/findByUser"),
            new AntPathRequestMatcher("/microemprendimientos/admin/estadisticasGenerales"),
            //RUBROS ROUTES
            new AntPathRequestMatcher("/rubros/admin/estadisticasPorUsuario"),
            //MENSAJE ROUTES
            new AntPathRequestMatcher("/mensaje"),
            new AntPathRequestMatcher("/mensaje/gestionado"),
            new AntPathRequestMatcher("/mensaje/{id}"),
            new AntPathRequestMatcher("/mensaje/estadistica"),
            //GESTIONINVERSION ROUTES
            new AntPathRequestMatcher("/gestionInversion/admin/create"),
            new AntPathRequestMatcher("/gestionInversion/admin/edit"),
            new AntPathRequestMatcher("/gestionInversion/admin/getAll"), //Esta en teoria no se utiliza
            new AntPathRequestMatcher("/gestionInversion/admin/logic/{idMicro}"),
            new AntPathRequestMatcher("/gestionInversion/admin/{idMicro}"),
            //ANSWER ROUTES
            new AntPathRequestMatcher("/answer/create"),
            new AntPathRequestMatcher("/answer/answersNotFull"),
            new AntPathRequestMatcher("/answer/{id}"),
            //QUESTION ROUTES
            new AntPathRequestMatcher("/question/initial"),
            new AntPathRequestMatcher("/question/secondary"),
            new AntPathRequestMatcher("/question/questionsNotActive"),
            new AntPathRequestMatcher("/question/{id}"),
            new AntPathRequestMatcher("/question/hide/{id}"),
            new AntPathRequestMatcher("/question/show/{id}"),
            new AntPathRequestMatcher("/question/text/{id}")
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
